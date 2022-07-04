package com.itv.leedstech.techtest.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itv.leedstech.techtest.constant.Params;
import com.itv.leedstech.techtest.model.PlayoutCostResponse;
import com.itv.leedstech.techtest.model.Programme;
import com.itv.leedstech.techtest.model.ProgrammeServiceResponse;
import com.itv.leedstech.techtest.model.ScheduleEntry;
import com.itv.leedstech.techtest.model.ScheduleEntryRequest;
import com.itv.leedstech.techtest.model.ScheduleRequest;
import com.itv.leedstech.techtest.repository.ProgrammeRepository;
import com.itv.leedstech.techtest.repository.ScheduleEntryRepository;

@Component
public class ProgrammeServiceImpl implements ProgrammeService {

	private static Logger logger = LoggerFactory.getLogger(ProgrammeServiceImpl.class);

	@Autowired
	private ProgrammeRepository programmeRepository;

	@Autowired
	private ScheduleEntryRepository scheduleEntryRepository;

	/***
	 * 
	 * @param programme
	 * @return ProgrammeServiceResponse
	 */
	public ProgrammeServiceResponse loadProgramme(Programme programme) {
		ProgrammeServiceResponse loadProgrammeResponse = new ProgrammeServiceResponse(true);
		logger.info("loadProgramme: begin");
		try {
			programmeRepository.save(programme);
		} catch (RuntimeException e) {
			logger.error(e.getMessage());
			loadProgrammeResponse.setOk(false);
		}
		logger.info("loadProgramme: End");
		return loadProgrammeResponse;
	}

	/***
	 * 
	 * @param scheduleEntryRequest
	 * @return ProgrammeServiceResponse
	 */
	@Override
	public ProgrammeServiceResponse loadScheduleEntry(ScheduleEntryRequest scheduleEntryRequest) {
		ProgrammeServiceResponse loadProgrammeResponse = new ProgrammeServiceResponse(true);
		logger.info("loadScheduleEntry: begin");
		try {
			String programme_id = scheduleEntryRequest.getProgrammeId();
			Programme programme = programmeRepository.getOne(programme_id);

			Double cost = programme.getPlayoutCost();
			ScheduleEntry scheduleEntry = new ScheduleEntry();
			scheduleEntry.setProgramme(programme);

			DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern(Params.DATE_FORMAT);
			String requestTimestamp = scheduleEntryRequest.getTimestamp();
			LocalDateTime timestamp = LocalDateTime.parse(requestTimestamp, myFormatObj);
			scheduleEntry.setTimestamp(timestamp);

			if (programme.getDiscountable()) {
				List<ScheduleEntry> listEntry = scheduleEntryRepository.findScheduleEntryByProgramme(programme);
				listEntry.sort(new ScheduleEntryComparator());

				if (listEntry.size() > 0) {
					ScheduleEntry last = listEntry.get(0);
					LocalDateTime lastTimestamp = last.getTimestamp();

					if ((!timestamp.isAfter(lastTimestamp.plusDays(7)) && (last.getDiscount() != 2))) {
						if ((timestamp.isBefore(lastTimestamp.plusDays(3))) && last.getDiscount() == 0) {
							scheduleEntry.setDiscount(1);
							scheduleEntry.setCost(0.0);
						} else if (last.getDiscount() == 1) {
							ScheduleEntry beforeLast = listEntry.get(1);
							if (timestamp.isBefore(beforeLast.getTimestamp().plusDays(7))) {
								scheduleEntry.setDiscount(2);
								cost = cost / 2;
							} else
								setScheduleEntry(scheduleEntry, Params.ZERO, cost);

						} else
							setScheduleEntry(scheduleEntry, Params.ZERO, cost);

					} else
						setScheduleEntry(scheduleEntry, Params.ZERO, cost);

				} else
					setScheduleEntry(scheduleEntry, Params.ZERO, cost);

			} else
				setScheduleEntry(scheduleEntry, Params.ZERO, cost);

			scheduleEntryRepository.save(scheduleEntry);
		} catch (RuntimeException e) {
			logger.error(e.getMessage());
			loadProgrammeResponse.setOk(false);
		}
		
		logger.info("loadProgramme: End");
		return loadProgrammeResponse;
	}

	/***
	 * 
	 * @param scheduleEntry
	 * @param discount
	 * @param cost
	 */
	private void setScheduleEntry(ScheduleEntry scheduleEntry, Integer discount, Double cost) {
		logger.info("setScheduleEntry: begin");
		scheduleEntry.setDiscount(discount);
		scheduleEntry.setCost(cost);
		logger.info("setScheduleEntry: End");
	}

	@Override
	public PlayoutCostResponse getPlayoutCost(ScheduleRequest scheduleRequest) {
		logger.info("getPlayoutCost: begin");
		PlayoutCostResponse playoutCostResponse = null;
		Double cost = 0.0;
		try {
			playoutCostResponse = new PlayoutCostResponse();
			playoutCostResponse.setOk(true);
			String programme_id = scheduleRequest.getProgrammeId();
			Programme programme = programmeRepository.getOne(programme_id);

			List<ScheduleEntry> scheduleEntryList = scheduleEntryRepository.findScheduleEntrySlotsCount(programme,
					scheduleRequest.getStart(), scheduleRequest.getEnd());

			Double[] listCost = new Double[1];
			listCost[0] = 0.0;
			scheduleEntryList.stream().forEach(x -> listCost[0] += x.getCost());
			cost = listCost[0];
			playoutCostResponse.setCost(cost);
		} catch (RuntimeException e) {
			logger.error(e.getMessage());
			playoutCostResponse.setOk(false);
		}
		logger.info("getPlayoutCost: End");
		return playoutCostResponse;

	}
}

class ScheduleEntryComparator implements Comparator<ScheduleEntry> {

	@Override
	public int compare(ScheduleEntry o1, ScheduleEntry o2) {
		if (o1.getTimestamp().isAfter(o2.getTimestamp())) {
			return -1;
		} else if (o1.getTimestamp().isBefore(o2.getTimestamp())) {
			return 1;
		} else {
			return 0;
		}
	}
}
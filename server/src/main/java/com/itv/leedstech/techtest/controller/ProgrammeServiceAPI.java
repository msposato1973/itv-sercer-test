package com.itv.leedstech.techtest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itv.leedstech.techtest.constant.Params;
import com.itv.leedstech.techtest.model.PlayoutCostResponse;
import com.itv.leedstech.techtest.model.Programme;
import com.itv.leedstech.techtest.model.ProgrammeServiceResponse;
import com.itv.leedstech.techtest.model.ScheduleEntryRequest;
import com.itv.leedstech.techtest.model.ScheduleRequest;

 

@RestController
@RequestMapping(Params.PROM_ROOT)
public class ProgrammeServiceAPI  extends BaseController {
	
	private static Logger logger = LoggerFactory.getLogger(ProgrammeServiceAPI.class);
	
	@PostMapping
	@RequestMapping(Params.LOAD_PROGRAM)
    public ResponseEntity<ProgrammeServiceResponse>
        loadProgramme(@RequestBody Programme programme) {
		logger.info("loadProgramme: begin");
        ProgrammeServiceResponse loadProgrammeResponse = programmeService.loadProgramme(programme);
        
        return prepareResponseEntity(loadProgrammeResponse);
    }

    @PostMapping
    @RequestMapping(Params.LOAD_SCHEDULE_ENTRY)
    public ResponseEntity<ProgrammeServiceResponse>
        loadScheduleEntry(@RequestBody ScheduleEntryRequest scheduleEntryRequest) {
    	logger.info("loadScheduleEntry: begin");
        ProgrammeServiceResponse loadProgrammeResponse = programmeService.loadScheduleEntry(scheduleEntryRequest);
        
        return prepareResponseEntity(loadProgrammeResponse);
    }

    @PostMapping
    @RequestMapping(Params.GET_PLAYOUT_COST)
    public ResponseEntity<PlayoutCostResponse>
        getPlayoutCost(@RequestBody ScheduleRequest scheduleRequest) {
    	logger.info("getPlayoutCost: begin");
        PlayoutCostResponse playoutCostResponse = programmeService.getPlayoutCost(scheduleRequest);
        
        return prepareResponseEntity(playoutCostResponse);
    }
}

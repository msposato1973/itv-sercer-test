package com.itv.leedstech.techtest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.itv.leedstech.techtest.model.PlayoutCostResponse;
import com.itv.leedstech.techtest.model.ProgrammeServiceResponse;
import com.itv.leedstech.techtest.service.ProgrammeService;

public class BaseController {
	private static Logger log = LoggerFactory.getLogger(BaseController.class);
	
	@Autowired
	protected ProgrammeService programmeService;
	
	/***
	 * 
	 * @param loadProgrammeResponse
	 * @return
	 */
	ResponseEntity<ProgrammeServiceResponse> prepareResponseEntity(ProgrammeServiceResponse loadProgrammeResponse) {
		log.info("prepareResponseEntity: begin");
		ResponseEntity<ProgrammeServiceResponse> response = new ResponseEntity<>(loadProgrammeResponse, HttpStatus.CREATED);
		return response;
	}
	
	/***
	 * 
	 * @param playoutCostResponse
	 * @return
	 */
	ResponseEntity<PlayoutCostResponse> prepareResponseEntity(PlayoutCostResponse playoutCostResponse) {
		ResponseEntity<PlayoutCostResponse> response = new ResponseEntity<PlayoutCostResponse>(playoutCostResponse, HttpStatus.ACCEPTED);
		log.info("prepareResponseEntity: begin");
		return response;
	}
}

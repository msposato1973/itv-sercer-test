package com.itv.leedstech.techtest.service;

import com.itv.leedstech.techtest.model.*;

@org.springframework.stereotype.Service
public interface ProgrammeService {
    public ProgrammeServiceResponse loadProgramme(Programme programme);
    public ProgrammeServiceResponse loadScheduleEntry(ScheduleEntryRequest scheduleEntryRequest);
    public PlayoutCostResponse getPlayoutCost(ScheduleRequest scheduleRequest);
}
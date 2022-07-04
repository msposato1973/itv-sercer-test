package com.itv.leedstech.techtest.constant;

import org.springframework.beans.factory.annotation.Value;

public final class Params {
	
	@Value("${date.format}")
    private static String dtFormat;
	
    public static final String PROM_ROOT = "/" + "api" + "/"+ "itv";
    public static final String LOAD_PROGRAM = "/" + "programme";
    public static final String LOAD_SCHEDULE =  LOAD_PROGRAM + "/" + "schedule" ;
    public static final String LOAD_SCHEDULE_ENTRY = LOAD_SCHEDULE + "/" + "entry";
    public static final String GET_PLAYOUT_COST = LOAD_SCHEDULE + "/" + "request";
    public static final String DATE_FORMAT = dtFormat;
    
    public static final Integer ZERO = 0;
    
}

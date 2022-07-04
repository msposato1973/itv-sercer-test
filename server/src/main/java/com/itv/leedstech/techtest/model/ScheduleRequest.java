package com.itv.leedstech.techtest.model;


import java.time.LocalDateTime;

public class ScheduleRequest {
    private String programmeId;
    private LocalDateTime start;
    private LocalDateTime end;
    
    public String getProgrammeId() {
		return programmeId;
	}

	public void setProgrammeId(String programmeId) {
		this.programmeId = programmeId;
	}

	public LocalDateTime getStart() {
		return start;
	}

	public void setStart(LocalDateTime start) {
		this.start = start;
	}

	public LocalDateTime getEnd() {
		return end;
	}

	public void setEnd(LocalDateTime end) {
		this.end = end;
	}

	
    
    public  ScheduleRequest() {
    	super();
    }
    
    public  ScheduleRequest(String programmeId, LocalDateTime start, LocalDateTime end ) {
    	super();
    	this.programmeId=programmeId;
    	this.start=start;
    	this.end=end;
    }
    
    public static ScheduleRequest.ScheduleRequestBuilder builder() {
        return new ScheduleRequest.ScheduleRequestBuilder();
    }
    
    public static class ScheduleRequestBuilder {
    	private String programmeId;
        private LocalDateTime start;
        private LocalDateTime end;
        
        ScheduleRequestBuilder() { }
        
        public ScheduleRequest.ScheduleRequestBuilder programmeId(final String programmeId) {
            this.programmeId = programmeId;
            return this;
        }

        public ScheduleRequest.ScheduleRequestBuilder start(final LocalDateTime start) {
            this.start = start;
            return this;
        }

        public ScheduleRequest.ScheduleRequestBuilder end(final  LocalDateTime end) {
            this.end = end;
            return this;
        }
        
        public ScheduleRequest build() {
            return new ScheduleRequest(this.programmeId, this.start, this.end);
        }

        public String toString() {
            return "ScheduleRequest.ScheduleRequestBuilder(programmeId=" + this.programmeId + ", start=" + this.start + ", end=" + this.end + ")";
        }
    }
}

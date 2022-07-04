package com.itv.leedstech.techtest.model;



public class ScheduleEntryRequest {
	
	@Override
	public String toString() {
		return "ScheduleEntryRequest [programmeId=" + programmeId + ", timestamp=" + timestamp + "]";
	}

	private String programmeId;
    private String timestamp;
    
    /***
     * 
     * @param programmeId
     * @param timestamp
     */
	public ScheduleEntryRequest(String programmeId, String timestamp) {
		this.programmeId=programmeId;
		this.timestamp=timestamp;
	}
	
	public ScheduleEntryRequest() {}
	
	/***
	 * 
	 * @return String programmeId
	 */
	public String getProgrammeId() {
		return programmeId;
	}
	/***
	 * 
	 * @param programmeId
	 */
	public void setProgrammeId(String programmeId) {
		this.programmeId = programmeId;
	}
	
	/***
	 * 
	 * @return String timestamp
	 */
	public String getTimestamp() {
		return timestamp;
	}
	
	/***
	 * 
	 * @param timestamp
	 */
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	
	 
	 public static ScheduleEntryRequest.ScheduleEntryRequestBuilder builder() {
	        return new ScheduleEntryRequest.ScheduleEntryRequestBuilder();
	 }
	 
	 
	 
	 public static class ScheduleEntryRequestBuilder {
		 private String programmeId;
		 private String timestamp;
		 
		 ScheduleEntryRequestBuilder() { }
		 
		 /***
		  * 
		  * @param programmeId
		  * @return
		  */
		 public ScheduleEntryRequest.ScheduleEntryRequestBuilder programmeId(final String programmeId) {
	            this.programmeId = programmeId;
	            return this;
	     }
		 
		 /***
		  * 
		  * @param timestamp
		  * @return
		  */
		 public ScheduleEntryRequest.ScheduleEntryRequestBuilder timestamp(final String timestamp) {
	            this.timestamp = timestamp;
	            return this;
	     }
		 
		 /****
		  * 
		  * @return
		  */
		 public ScheduleEntryRequest build() {
	            return new ScheduleEntryRequest(this.programmeId, this.timestamp);
	     }
		 
		  
		 public String toString() {
	            return "ScheduleEntryRequest.ScheduleEntryRequestBuilder(programmeId=" + this.programmeId + ", timestamp=" + this.timestamp + ")";
	     }
	}
}
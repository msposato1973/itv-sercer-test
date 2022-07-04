package com.itv.leedstech.techtest.model;

public class PlayoutCostResponse extends ProgrammeServiceResponse {

	private Double cost;
	
    public String toString() {
		return "PlayoutCostResponse [cost=" + cost + "]";
	}

	public PlayoutCostResponse() { }
    
    public PlayoutCostResponse( Double cost) {
    	this.cost = cost;
    }

    public Double getCost() {
		return cost;
    }

    public void setCost(Double cost) {
		this.cost = cost;
    }

    
    
    
}

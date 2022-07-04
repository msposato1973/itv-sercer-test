package com.itv.leedstech.techtest.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
 
@Entity
@Table(name = "PROGRAMME")
@Data
public class Programme {
	@Id
    private String id;
    
	@Column
    private String title;
    
	@Column
    private Double playoutCost;
    
	@Column
    private Boolean discountable;

    public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Programme(String id, String title, Double playoutCost, Boolean discountable) {
		super();
		this.id = id;
		this.title = title;
		this.playoutCost = playoutCost;
		this.discountable = discountable;
	}
	
	public Programme( Boolean discountable) {
		super();
		this.discountable = discountable;
	}
	
	public Programme() {
		super();
	}
	
	public static Programme.ProgrammeBuilder builder() {
        return new Programme.ProgrammeBuilder();
    }
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Double getPlayoutCost() {
		return playoutCost;
	}
	public void setPlayoutCost(Double playoutCost) {
		this.playoutCost = playoutCost;
	}
	public Boolean getDiscountable() {
		return discountable;
	}
	public void setDiscountable(Boolean discountable) {
		this.discountable = discountable;
	}
	
	@Override
	public String toString() {
		return "Programme [id=" + id + ", title=" + title + ", playoutCost=" + playoutCost + ", discountable="
				+ discountable + "]";
	}
	
	public static class ProgrammeBuilder {
	    private String id; 
	    private String title;
	    private Double playoutCost;
	    private Boolean discountable;
	    
        ProgrammeBuilder() {}

        public Programme.ProgrammeBuilder id(final String id) {
            this.id = id;
            return this;
        }

        public Programme.ProgrammeBuilder title(final String title) {
            this.title = title;
            return this;
        }

        public Programme.ProgrammeBuilder playoutCost(final Double playoutCost) {
            this.playoutCost = playoutCost;
            return this;
        }
        
        public Programme.ProgrammeBuilder discountable(final Boolean discountable) {
            this.discountable = discountable;
            return this;
        }

        public Programme build() {
        	 return new Programme(this.id, this.title, this.playoutCost, this.discountable);
        }

        public String toString() {
            return "BuilderTestRequest.BuilderTestRequestBuilder(id=" + this.id + ", title=" + this.title + ", playoutCost=" + this.playoutCost + ", discountable=" + this.discountable + ")";
        }
	}
	 
	 
}

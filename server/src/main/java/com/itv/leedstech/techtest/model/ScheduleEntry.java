package com.itv.leedstech.techtest.model;


import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
 

@Entity
@Table(name = "SCHEDULE_ENTRY")
public class ScheduleEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    
    @Column
    private LocalDateTime timestamp;
    
    @Column
    private Double cost;
    
    @Column
    private Integer discount;
    
    @ManyToOne
    @JoinColumn(name = "programme_id")
    private Programme programme;
	
    public ScheduleEntry() {}
    
    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
	public Double getCost() {
		return cost;
	}
	public void setCost(Double cost) {
		this.cost = cost;
	}
	public Integer getDiscount() {
		return discount;
	}
	public void setDiscount(Integer discount) {
		this.discount = discount;
	}
	public Programme getProgramme() {
		return programme;
	}
	public void setProgramme(Programme programme) {
		this.programme = programme;
	}

	public ScheduleEntry(Long id, LocalDateTime timestamp, Double cost, Integer discount, Programme programme) {
		super();
		this.id = id;
		this.timestamp = timestamp;
		this.cost = cost;
		this.discount = discount;
		this.programme = programme;
	}

	@Override
	public String toString() {
		return "ScheduleEntry [id=" + id + ", timestamp=" + timestamp + ", cost=" + cost + ", discount=" + discount
				+ ", programme=" + programme + "]";
	}
    
    
}


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

import lombok.AllArgsConstructor;

@Entity
@Table(name = "SCHEDULE")
public class Schedule {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    
    @Column
    private LocalDateTime start;
    
    @Column
    private LocalDateTime end;
    
    @ManyToOne
    @JoinColumn(name = "programme_id")
    private Programme programme;
    
    public Schedule() {}
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public Programme getProgramme() {
		return programme;
	}
	public void setProgramme(Programme programme) {
		this.programme = programme;
	}

	public Schedule(Long id, LocalDateTime start, LocalDateTime end, Programme programme) {
		super();
		this.id = id;
		this.start = start;
		this.end = end;
		this.programme = programme;
	}
    
    
}

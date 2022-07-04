package com.itv.leedstech.techtest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.itv.leedstech.techtest.model.Programme;

@Repository
public interface ProgrammeRepository extends JpaRepository<Programme,String> {

}
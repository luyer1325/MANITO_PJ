package com.leeds.manito.manito_pj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.leeds.manito.manito_pj.entity.MissionInfo;

@Repository
public interface MissionRepositiory extends JpaRepository<MissionInfo, Integer> {

}
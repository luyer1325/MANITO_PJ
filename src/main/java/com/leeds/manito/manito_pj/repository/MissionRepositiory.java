package com.leeds.manito.manito_pj.repository;

import java.util.Optional;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.leeds.manito.manito_pj.entity.MissionInfo;

@Repository
public interface MissionRepositiory extends JpaRepository<MissionInfo, Integer> {
    Optional<MissionInfo> findBymissionIdx(int missionIdx);

    List<MissionInfo> findBymanitoIdx(int manitoIdx);
}
package com.leeds.manito.manito_pj.dto;

import java.util.ArrayList;
import java.util.List;

public class Missions extends GameInfo {
    private List<MissionInfoDTO> missions;
    private String missionTime;
    private int degree;

    public Missions() {
        this.missions = new ArrayList<>();
    }

    public void addMissions(MissionInfoDTO missionInfoDTO) {
        this.missions.add(missionInfoDTO);
    }

    public List<MissionInfoDTO> getMissions() {
        return this.missions;
    }

    public void setMissions(List<MissionInfoDTO> missions) {
        this.missions = missions;
    }

    public String getMissionTime() {
        return this.missionTime;
    }

    public void setMissionTime(String missionTime) {
        this.missionTime = missionTime;
    }

    public int getDegree() {
        return this.degree;
    }

    public void setDegree(int degree) {
        this.degree = degree;
    }

}
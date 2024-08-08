package com.leeds.manito.manito_pj.dto;

import java.util.List;

public class MissionGroup {
    private int degree;
    private List<MissionInfoDTO> missions;
    private String missionTime;

    public int getDegree() {
        return this.degree;
    }

    public void setDegree(int degree) {
        this.degree = degree;
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

}
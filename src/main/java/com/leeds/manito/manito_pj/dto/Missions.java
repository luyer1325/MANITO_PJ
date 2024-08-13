package com.leeds.manito.manito_pj.dto;

import java.util.ArrayList;
import java.util.List;

public class Missions {
    private List<MissionInfoDTO> missions;

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

}
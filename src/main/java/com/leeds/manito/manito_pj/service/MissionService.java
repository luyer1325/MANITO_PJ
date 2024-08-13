package com.leeds.manito.manito_pj.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.google.gson.reflect.TypeToken;
import com.leeds.manito.manito_pj.dto.ManitoInfoDTO;
import com.leeds.manito.manito_pj.dto.MissionInfoDTO;
import com.leeds.manito.manito_pj.dto.Missions;
import com.leeds.manito.manito_pj.entity.ManitoInfo;
import com.leeds.manito.manito_pj.entity.MissionInfo;
import com.leeds.manito.manito_pj.repository.ManitoRepository;
import com.leeds.manito.manito_pj.repository.MissionRepositiory;

@Service
public class MissionService {
    @Autowired
    ManitoRepository manitoRepository;

    @Autowired
    MissionRepositiory missionRepositiory;

    // @Autowired
    // ModelMapper modelMapper;
    private final ModelMapper modelMapper;

    public MissionService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public int CreateMission(MissionInfoDTO missionInfoDTO) {
        MissionInfo mi = MissionInfo.builder()
                .degree(missionInfoDTO.getDegree())
                .content(missionInfoDTO.getContent())
                .missionTime(missionInfoDTO.getMissionTime())
                .manitoIdx(missionInfoDTO.getManitoIdx())
                .build();
        this.missionRepositiory.save(mi);
        return this.missionRepositiory.save(mi).getMissionIdx();
    }

    public Missions TempMissions(int manitoIdx, String startDate) {
        Missions missions = new Missions();
        MissionInfoDTO tempMission = new MissionInfoDTO();
        tempMission.setManitoIdx(manitoIdx);
        tempMission.setMissionTime(startDate);
        missions.addMissions(tempMission);
        return missions;
    }

    public Missions addTempMissions(Missions missions) {
        MissionInfoDTO tempMission = new MissionInfoDTO();
        List<MissionInfoDTO> tempList = missions.getMissions();
        tempMission.setManitoIdx(tempList.get(0).getManitoIdx());
        tempMission.setMissionTime(tempList.get(0).getMissionTime());
        System.out.println("여기 : " + tempList.get(0).getMissionTime());
        missions.addMissions(tempMission);
        return missions;
    }

    // public List<MissionInfoDTO> getAllMissions(Model model, int idx) {
    // List<MissionInfo> missionInfos = missionRepositiory.findBymanitoIdx(idx);

    // for(int i = 0; i < missionInfos.size(); i++){
    // MissionInfo missionInfo = new MissionInfo();
    // modelMapper.map(missionInfo, MissionInfoDTO.class);
    // }
    // }

}

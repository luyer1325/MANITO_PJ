package com.leeds.manito.manito_pj.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leeds.manito.manito_pj.dto.MissionInfoDTO;
import com.leeds.manito.manito_pj.dto.Missions;
import com.leeds.manito.manito_pj.entity.MissionInfo;
import com.leeds.manito.manito_pj.repository.ManitoRepository;
import com.leeds.manito.manito_pj.repository.MissionRepository;

@Service
public class MissionService {
    @Autowired
    ManitoRepository manitoRepository;

    @Autowired
    MissionRepository missionRepository;

    // @Autowired
    // ModelMapper modelMapper;
    private final ModelMapper modelMapper;

    public MissionService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    // db mission 저장
    public void createMission(Missions missions) {
        List<MissionInfoDTO> missionList = missions.getMissions();
        // List<Integer> missionIdxList = new ArrayList<>();
        missionList.forEach((item) -> {
            MissionInfo mi = MissionInfo.builder()
                    .degree(item.getDegree())
                    .content(item.getContent())
                    .missionTime(item.getMissionTime())
                    .manitoIdx(item.getManitoIdx())
                    .build();
            this.missionRepository.save(mi).getMissionIdx();
            // int num = this.missionRepositiory.save(mi).getMissionIdx();
            // missionIdxList.add(num);
        });
        // return missionIdxList;
    }

    // db mission 불러오기
    public Missions searchMissions(int manitoIdx) {
        List<MissionInfo> missionList = missionRepository.findAllBymanitoIdx(manitoIdx);
        List<MissionInfoDTO> missions = missionList.stream()
                .map(missionInfo -> modelMapper.map(missionInfo, MissionInfoDTO.class))
                .collect(Collectors.toList());
        Missions missions2 = new Missions();
        missions2.setMissions(missions);
        return missions2;
    }

    public List<Missions> groupingMissions(Missions missions) {
        List<Missions> groups = new ArrayList<>();
        // List<MissionsInfoDTO>
        // degree가 같은 애들을 new missions에 넣어서 groups에 add
        Map<Integer, List<MissionInfoDTO>> grouping = missions.getMissions().stream()
                .collect(Collectors.groupingBy(MissionInfoDTO::getDegree));
        Set<Integer> keySet = grouping.keySet();
        // for loop
        for (Integer key : keySet) {
            Missions mission = new Missions();
            mission.setMissions(grouping.get(key));
            groups.add(mission);
        }
        return groups;
    }
    // public int CreateMission(MissionInfoDTO missionInfoDTO) {
    // MissionInfo mi = MissionInfo.builder()
    // .degree(missionInfoDTO.getDegree())
    // .content(missionInfoDTO.getContent())
    // .missionTime(missionInfoDTO.getMissionTime())
    // .manitoIdx(missionInfoDTO.getManitoIdx())
    // .build();
    // this.missionRepositiory.save(mi);
    // return this.missionRepositiory.save(mi).getMissionIdx();
    // }

    public Missions TempMissions(int manitoIdx, String startDate) {
        Missions missions = new Missions();
        MissionInfoDTO tempMission = new MissionInfoDTO();
        tempMission.setManitoIdx(manitoIdx);
        tempMission.setMissionTime(startDate);
        missions.addMissions(tempMission);
        return missions;
    }

    public Missions TempMissions(int manitoIdx, String startDate, int degree) {
        Missions missions = new Missions();
        MissionInfoDTO tempMission = new MissionInfoDTO();
        tempMission.setManitoIdx(manitoIdx);
        tempMission.setDegree(degree);
        tempMission.setMissionTime(startDate);
        missions.addMissions(tempMission);
        return missions;
    }

    public Missions addTempMissions(Missions missions) {
        MissionInfoDTO tempMission = new MissionInfoDTO();
        List<MissionInfoDTO> tempList = missions.getMissions();
        tempMission.setManitoIdx(tempList.get(0).getManitoIdx());
        tempMission.setDegree(tempList.get(0).getDegree());
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

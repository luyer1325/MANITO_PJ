package com.leeds.manito.manito_pj.service;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.google.gson.reflect.TypeToken;
import com.leeds.manito.manito_pj.dto.ManitoInfoDTO;
import com.leeds.manito.manito_pj.dto.MissionInfoDTO;
import com.leeds.manito.manito_pj.dto.UserInfoDTO;
import com.leeds.manito.manito_pj.entity.ManitoInfo;
import com.leeds.manito.manito_pj.entity.MissionInfo;
import com.leeds.manito.manito_pj.repository.ManitoRepository;
import com.leeds.manito.manito_pj.repository.MissionRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class ManitoService {

    @Autowired
    ManitoRepository manitoRepository;

    @Autowired
    MissionRepository missionRepository;

    ManitoInfo manito = new ManitoInfo();
    List<MissionInfo> mission = new ArrayList<MissionInfo>();

    // @Autowired
    // ModelMapper modelMapper;
    private final ModelMapper modelMapper;

    public ManitoService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Value("${kakao_js_key}")
    private String jsKey;

    /*
     * 마니또 게임의 생성 메소드
     * 
     * @ param ManitoInfoDTO
     * 
     * @ return ManitoInfoDTO.getManito_idx
     */
    public Integer CreateManito(ManitoInfoDTO manitoInfoDTO) {
        ManitoInfo me = ManitoInfo.builder()
                .status("B")
                .joinYn(manitoInfoDTO.getJoinYn())
                .showYn(manitoInfoDTO.getShowYn())
                .missionYn(manitoInfoDTO.getMissionYn())
                .createUser(manitoInfoDTO.getCreateUser())
                .created(manitoInfoDTO.getCreated())
                .endDate(manitoInfoDTO.getEndDate())
                .startDate(manitoInfoDTO.getStartDate())
                .build();
        int idx = this.manitoRepository.save(me).getManitoIdx();
        return idx;
    }

    public void CreateMission(MissionInfoDTO missionInfoDTO) {
        MissionInfo mi = MissionInfo.builder()
                .degree(missionInfoDTO.getDegree())
                .content(missionInfoDTO.getContent())
                .missionTime(missionInfoDTO.getMissionTime())
                .manitoIdx(missionInfoDTO.getManitoIdx())
                .build();
        this.missionRepository.save(mi);
    }

    public ManitoInfoDTO checkLogin(UserInfoDTO userInfoDTO) {
        System.out.println("현재 createUser :" + userInfoDTO.getUserId());
        ManitoInfo manitoInfo = manitoRepository
                .findByCreateUserOrManitoIdx(userInfoDTO.getUserId(), userInfoDTO.getManitoIdx())
                .orElseGet(() -> manito);
        return modelMapper.map(manitoInfo, ManitoInfoDTO.class);
    }

    public void getInfo(Model model, HttpSession session) {
        model.addAttribute("at", session.getAttribute("at"));
        model.addAttribute("kakao_js_key", jsKey);
    }

    public boolean missionYN(int manitoIdx) {
        ManitoInfo manitoInfo = manitoRepository.findById(manitoIdx).orElseGet(() -> manito);
        if (manitoInfo.getMissionYn().equals("Y")) {
            return true;
        } else {
            return false;
        }
    }

    public ManitoInfo showInfo(int manitoIdx) {
        ManitoInfo manitoInfo = manitoRepository.findById(manitoIdx).orElseGet(() -> manito);
        return manitoInfo;
    }

    public List<MissionInfoDTO> getAllMissions(int manitoIdx) {
        List<MissionInfo> missionInfoList = missionRepository.findAllBymanitoIdx(manitoIdx);
        return modelMapper.map(missionInfoList, new TypeToken<List<MissionInfoDTO>>() {
        }.getType());
    }

    public void testSetting(Model model) {
        model.addAttribute("target", "이정한");
        model.addAttribute("manitoName", "토마토마토");
        model.addAttribute("endDate", "2024-08-18 16:20");
        ArrayList<String> list = new ArrayList<String>();
        list.add("토마토마토");
        list.add("양파파");
        list.add("치커리");
        list.add("데굴데굴감자");
        list.add("냐옹배추");
        model.addAttribute("memberlist", list);
    }

    public void testEndSetting(Model model) {
        ArrayList<String> list = new ArrayList<String>();
        list.add("이정한");
        list.add("안주현");
        list.add("김희진");
        list.add("김호동");
        list.add("김다영");
        model.addAttribute("memberlist", list);
    }

    public void testEndSetting2(Model model) {
        model.addAttribute("manito", "이정한");
        model.addAttribute("manitoName", "토마토마토");
        Map<Integer, String> mission = new HashMap<Integer, String>();
        mission.put(1, "마니또에게 응원의 카톡 매일 하나씩 보내기");
        mission.put(2, "마니또에게 달콤한 선물 보내기");
        model.addAttribute("mission", mission);
    }
}

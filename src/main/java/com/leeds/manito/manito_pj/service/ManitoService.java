package com.leeds.manito.manito_pj.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.tags.shaded.org.apache.xpath.operations.Mod;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.thymeleaf.expression.Maps;

import com.leeds.manito.manito_pj.dto.ManitoInfoDTO;
import com.leeds.manito.manito_pj.entity.ManitoInfo;
import com.leeds.manito.manito_pj.repository.ManitoRepository;

@Service
public class ManitoService {

    @Autowired
    ManitoRepository manitoRepository;

    //@Autowired
    //ModelMapper modelMapper;
    private final ModelMapper modelMapper;

    @Autowired
    public ManitoService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    /*
     * 마니또 게임의 생성 메소드
     * 
     * @ param ManitoInfoDTO
     * 
     * @ return ManitoInfoDTO.getManito_idx
     */
    public Integer CreateManito(ManitoInfoDTO manitoInfoDTO) {
        ManitoInfo me = ManitoInfo.builder()
                .joinYn(manitoInfoDTO.getJoin_yn())
                .showYn(manitoInfoDTO.getShow_yn())
                .missionYn(manitoInfoDTO.getMission_yn())
                .createUser("test")
                .created(manitoInfoDTO.getCreated())
                .endDate(manitoInfoDTO.getEnd_date())
                .build();
        this.manitoRepository.save(me);
        return manitoInfoDTO.getManito_idx();
    }
    public ManitoInfoDTO checkLogin(Model model,ManitoInfoDTO manitoInfoDTO){
        ManitoInfo manitoInfo = manitoRepository.findByCreateUser(manitoInfoDTO.getCreate_user());
        return modelMapper.map(manitoInfo, ManitoInfoDTO.class);
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

package com.leeds.manito.manito_pj.controller.ajh;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.google.gson.Gson;
import com.leeds.manito.manito_pj.dto.ManitoInfoDTO;
import com.leeds.manito.manito_pj.dto.MissionInfoDTO;
import com.leeds.manito.manito_pj.dto.Missions;
import com.leeds.manito.manito_pj.entity.ManitoInfo;
import com.leeds.manito.manito_pj.service.ManitoService;
import com.leeds.manito.manito_pj.service.MissionService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MainPageController {

    @Autowired
    ManitoService manitoService;

    @Autowired
    MissionService missionService;

    @GetMapping("/ajh.do")
    public String getMethodName(Model model, ManitoInfoDTO manitoInfoDTO) {
        System.out.println("주현페이지");
        return "thymeleaf/ajh/index";
    }

    @GetMapping("/ajh2.do")
    public String make(Model model, ManitoInfoDTO manitoInfoDTO) {
        System.out.println("게임생성");
        return "thymeleaf/ajh/makeGame";
    }

    @PostMapping("/ajh3.do")
    public String accept(Model model, ManitoInfoDTO manitoInfoDTO) {
        int idx = manitoService.CreateManito(manitoInfoDTO);
        ManitoInfo manito = manitoService.showInfo(idx);
        System.out.println("세부설정" + idx + "마니또 엔티티 : " + manito.getStartDate());
        model.addAttribute("manitoIdx", idx);
        Missions missions = missionService.TempMissions(idx, manito.getStartDate());
        model.addAttribute("form", missions);
        if (manito.getMissionYn().equals("Y")) {
            return "thymeleaf/ajh/gameDetail";
        } else {
            return "thymeleaf/ajh/start";
        }
    }

    @RequestMapping("/ajh3.do/add")
    public String getMethodName(Model model, Missions missions) {
        missionService.addTempMissions(missions);
        model.addAttribute("form", missions);
        return "thymeleaf/ajh/gameDetail";
    }

    // @PostMapping("/test3.do")
    // public String test3(Model model, MissionGroup missionGroup) {
    // model.addAttribute("missionGroup", missionGroup);
    // return "thymeleaf/ajh/showDetail";
    // }

    @RequestMapping("/ajh4.do")
    public String detail(Model model, ManitoInfoDTO manitoInfoDTO) {
        System.out.println("게임시작" + model.getAttribute("manitoIdx"));
        // manitoService.testSetting(model);
        // return "thymeleaf/ajh/start";
        return "thymeleaf/start";
    }

    // @RequestMapping("/ajh4.do")
    // public String start(Model model, ManitoInfoDTO manitoInfoDTO) {
    // System.out.println("게임시작" + model.getAttribute("manitoIdx"));
    // // manitoService.testSetting(model);
    // // return "thymeleaf/ajh/start";
    // return "thymeleaf/gameDetail"; // 임시경로
    // }

    @GetMapping("/ajh5.do")
    public String end(Model model, ManitoInfoDTO manitoInfoDTO) {
        System.out.println("게임종료");
        manitoService.testEndSetting(model);
        return "thymeleaf/ajh/end";
    }

    @PostMapping("/ajh6.do")
    public String end2(Model model, ManitoInfoDTO manitoInfoDTO) {
        System.out.println("게임종료2");
        manitoService.testEndSetting2(model);
        return "thymeleaf/ajh/end2";
    }

    @PostMapping("/test2.do")
    public String test2(Model model, @RequestBody String jsonData, @ModelAttribute MissionInfoDTO missionInfoDTO) {
        Gson gson = new Gson();
        MissionInfoDTO mission = gson.fromJson(jsonData, MissionInfoDTO.class);
        System.out.println("세팅완료" + mission);
        // System.out.println("getContent" + mission.getContent());
        // manitoService.CreateMission(mission);
        return "thymeleaf/ajh/showDetail";
    }

    @PostMapping("/test.do")
    public String test(Model model, @RequestBody String jsonData, @ModelAttribute MissionInfoDTO missionInfoDTO) {
        Gson gson = new Gson();
        MissionInfoDTO mission = gson.fromJson(jsonData, MissionInfoDTO.class);
        System.out.println("세팅완료");
        manitoService.CreateMission(mission);
        System.out.println("manitoIdx : " + mission.getManitoIdx());
        model.addAttribute("manitoIdx", mission.getManitoIdx());
        List<MissionInfoDTO> missionInfoList = manitoService.getAllMissions(mission.getManitoIdx());
        model.addAttribute("missionGroup", missionInfoList);
        return "thymeleaf/ajh/showDetail";
    }

}

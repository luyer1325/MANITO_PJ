package com.leeds.manito.manito_pj.controller.ajh;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.google.gson.Gson;
import com.leeds.manito.manito_pj.dto.ManitoInfoDTO;
import com.leeds.manito.manito_pj.dto.MissionInfoDTO;
import com.leeds.manito.manito_pj.service.ManitoService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class MainPageController {

    @Autowired
    ManitoService manitoService;

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
        boolean missionYn = manitoService.missionYN(idx);
        System.out.println("세부설정" + idx + "미션여부 : " + missionYn);
        model.addAttribute("manitoIdx", idx);
        if (missionYn) {
            return "thymeleaf/ajh/gameDetail";
        } else {
            return "thymeleaf/ajh/start";
        }
    }

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
    public void test2(Model model, @RequestBody String jsonData, @ModelAttribute MissionInfoDTO missionInfoDTO) {
        Gson gson = new Gson();
        MissionInfoDTO mission = gson.fromJson(jsonData, MissionInfoDTO.class);
        System.out.println("세팅완료");
        System.out.println("getContent" + mission.getContent());
        manitoService.CreateMission(mission);
        // return "thymeleaf/ajh/test";
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

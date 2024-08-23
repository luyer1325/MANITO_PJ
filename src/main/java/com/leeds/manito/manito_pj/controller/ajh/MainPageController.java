package com.leeds.manito.manito_pj.controller.ajh;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
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
        // 기본 manito 정보 전달
        // model.addAttribute("manitoIdx", idx);
        // model.addAttribute("sDate", manito.getStartDate());
        // model.addAttribute("eDate", manito.getEndDate());
        // missions 빈 객체 전달
        model.addAttribute("form",
                missionService.initMission(idx, manito.getStartDate(), manito.getEndDate()));
        if (manito.getMissionYn().equals("Y")) {
            return "thymeleaf/ajh/gameDetail";
        } else {
            return "thymeleaf/ajh/start";
        }
    }

    @PostMapping("/ajh3.do/update/mission/add")
    public String addMission(Model model,
            @RequestBody String jsonData) {
        Gson gson = new Gson();
        Type list = new TypeToken<List<MissionInfoDTO>>() {
        }.getType();
        List<MissionInfoDTO> gsonMission = gson.fromJson(jsonData, list);
        List<MissionInfoDTO> missions = missionService.addMission(gsonMission);
        Missions mission = new Missions();
        mission.setMissions(missions);
        // System.out.println("미션내용!! " + gson.toJson(missions));
        // missionService.addMission(missions);
        // model.addAttribute("form", missions);
        model.addAttribute("missions", mission.getMissions());
        // return "thymeleaf/ajh/gameDetail :: #formContainer";
        return "thymeleaf/ajh/gtest :: #ms";

    }

    @PostMapping("/ajh3.do/update/mission/remove")
    public String removeMission(Model model,
            @RequestBody String jsonData, String num) {
        Gson gson = new Gson();
        Missions missions = gson.fromJson(jsonData, Missions.class);
        model.addAttribute("form", missions);
        return "thymeleaf/ajh/gameDetail :: #formContainer";
    }

    // @PostMapping("/ajh3.do/update/group/add")
    // public String addGroup(Model model,
    // @RequestParam String eDate,
    // @RequestParam String sDate,
    // @RequestParam String manitoIdx,
    // @RequestParam String degree) {
    // System.out.println("eDate:" + eDate + "sDate:" + sDate + "manitoIdx:" +
    // manitoIdx + "degree:" + degree);
    // int idx = Integer.parseInt(manitoIdx);
    // int degreeNum = Integer.parseInt(degree);
    // // 기본 manito 정보 전달
    // model.addAttribute("manitoIdx", idx);
    // model.addAttribute("sDate", sDate);
    // model.addAttribute("eDate", eDate);
    // // missions 빈 객체 전달
    // model.addAttribute("form",
    // missionService.addTempMissions(missionService.initMission(idx, sDate,
    // degreeNum)));
    // return "thymeleaf/ajh/gameDetail";
    // }

    @PostMapping("/ajh3.do/register")
    public String test3(Model model, Missions missions,
            String eDate,
            String sDate,
            String manitoIdx,
            RedirectAttributes rttr) {
        Gson gson = new Gson();

        System.out.println(gson.toJson(missions));
        missionService.createMission(missions);

        // Missions missions2 = missionService.searchMissions(idx);
        // model.addAttribute("form", missions);
        // List<Missions> groups = new ArrayList<>();
        // groups.add(missions2);
        // groups.add(missions2);
        // model.addAttribute("a", groups);
        rttr.addFlashAttribute("manitoIdx", manitoIdx);
        rttr.addFlashAttribute("sDate", sDate);
        rttr.addFlashAttribute("eDate", eDate);
        return "redirect:/ajh3.do/view";
    }

    @RequestMapping("/ajh3.do/view")
    public String postMethodName(Model model,
            @ModelAttribute("manitoIdx") String manitoIdx,
            @ModelAttribute("eDate") String eDate,
            @ModelAttribute("sDate") String sDate) {
        // String manitoIdx = (String) model.getAttribute("manitoIdx");
        // String sDate = (String) model.getAttribute("sDate");
        // String eDate = (String) model.getAttribute("eDate");
        System.out.println(manitoIdx);
        int idx = Integer.parseInt(manitoIdx);
        List<Missions> groups = new ArrayList<>();
        Missions missions = missionService.searchMissions(idx);
        groups.add(missions);
        groups.add(missions);
        model.addAttribute("a", groups);
        model.addAttribute("manitoIdx", manitoIdx);
        model.addAttribute("sDate", sDate);
        model.addAttribute("eDate", eDate);
        return "thymeleaf/ajh/showDetail";
    }

    @RequestMapping("/ajh4.do")
    public String detail(Model model, ManitoInfoDTO manitoInfoDTO) {
        System.out.println("게임시작:" + model.getAttribute("manitoIdx"));
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

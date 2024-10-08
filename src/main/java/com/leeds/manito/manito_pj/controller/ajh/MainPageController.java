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

import jakarta.servlet.http.HttpSession;

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
        model.addAttribute("manitoIdx", idx);
        model.addAttribute("sDate", manito.getStartDate());
        model.addAttribute("eDate", manito.getEndDate());
        // missions 빈 객체 전달
        model.addAttribute("form",
                missionService.TempMissions(idx, manito.getStartDate()));
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
        Missions missions = gson.fromJson(jsonData, Missions.class);
        missionService.addTempMissions(missions);
        model.addAttribute("form", missions);
        return "thymeleaf/ajh/gameDetail :: #formContainer";
    }

    @PostMapping("/ajh3.do/update/mission/remove")
    public String removeMission(Model model,
            @RequestBody String jsonData) {
        Gson gson = new Gson();
        Missions missions = gson.fromJson(jsonData, Missions.class);
        model.addAttribute("form", missions);
        return "thymeleaf/ajh/gameDetail :: #formContainer";
    }

    @PostMapping("/ajh3.do/update/group/add")
    public String addGroup(Model model,
            @RequestParam String manitoIdx,
            @RequestParam String degree) {
        System.out.println("그룹추가" + "manitoIdx:" + manitoIdx + "degree:" + degree);
        int idx = Integer.parseInt(manitoIdx);
        int degreeNum = Integer.parseInt(degree);
        String eDate = manitoService.showInfo(idx).getEndDate();
        String sDate = manitoService.showInfo(idx).getStartDate();
        // 기본 manito 정보 전달
        model.addAttribute("manitoIdx", idx);
        model.addAttribute("sDate", sDate);
        model.addAttribute("eDate", eDate);
        // missions 빈 객체 전달
        model.addAttribute("form",
                missionService.TempMissions(idx, sDate, degreeNum));
        return "thymeleaf/ajh/gameDetail";
    }

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
        return "redirect:/ajh3.do/view";
    }

    @RequestMapping("/ajh3.do/view")
    public String postMethodName(HttpSession session, Model model) {
        String manitoIdx = (String) model.getAttribute("manitoIdx");
        if (manitoIdx == null) { // 새로고침
            manitoIdx = (String) session.getAttribute("manitoIdxS");
            System.out.println("새로고침" + session.getAttribute("manitoIdxS"));
        } else { // 첫 진입
            session.setAttribute("manitoIdxS", manitoIdx);
            System.out.println("첫진입" + (String) session.getAttribute("manitoIdxS"));
        }
        int idx = Integer.parseInt(manitoIdx);
        Missions missions = missionService.searchMissions(idx);
        List<Missions> groups = missionService.groupingMissions(missions);
        model.addAttribute("a", groups);
        model.addAttribute("form", missions);
        model.addAttribute("manitoIdx", idx);
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

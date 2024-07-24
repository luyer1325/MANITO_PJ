package com.leeds.manito.manito_pj.controller.ajh;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.leeds.manito.manito_pj.dto.ManitoInfoDTO;
import com.leeds.manito.manito_pj.service.ManitoService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
        manitoService.CreateManito(manitoInfoDTO);
        System.out.println("초대받음");
        return "thymeleaf/ajh/accept";
    }

    @RequestMapping("/ajh4.do")
    public String start(Model model, ManitoInfoDTO manitoInfoDTO) {
        System.out.println("게임시작");
        manitoService.testSetting(model);
        // return "thymeleaf/ajh/start";
        return "thymeleaf/gameDetail"; // 임시경로
    }

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
}

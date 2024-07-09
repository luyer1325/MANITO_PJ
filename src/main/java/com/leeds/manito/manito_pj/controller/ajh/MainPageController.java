package com.leeds.manito.manito_pj.controller.ajh;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.leeds.manito.manito_pj.dto.ManitoInfoDTO;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainPageController {
    @GetMapping("/ajh.do")
    public String getMethodName(Model model, ManitoInfoDTO roomInfoDTO) {
        System.out.println("주현페이지");
        return "thymeleaf/ajh/index";
    }

    @GetMapping("/ajh2.do")
    public String make(Model model, ManitoInfoDTO roomInfoDTO) {
        System.out.println("게임생성");
        return "thymeleaf/ajh/makeGame";
    }

    @PostMapping("/ajh3.do")
    public String accept(Model model, ManitoInfoDTO roomInfoDTO) {
        System.out.println("초대받음");
        return "thymeleaf/ajh/accept";
    }

    @GetMapping("/ajh4.do")
    public String start(Model model, ManitoInfoDTO roomInfoDTO) {
        System.out.println("게임시작");
        return "thymeleaf/ajh/start";
    }

    @GetMapping("/ajh5.do")
    public String end(Model model, ManitoInfoDTO roomInfoDTO) {
        System.out.println("게임종료");
        return "thymeleaf/ajh/end";
    }

    @GetMapping("/ajh6.do")
    public String end2(Model model, ManitoInfoDTO roomInfoDTO) {
        System.out.println("게임종료2");
        return "thymeleaf/ajh/end2";
    }
}

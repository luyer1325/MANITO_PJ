package com.leeds.manito.manito_pj.controller.ajh;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.leeds.manito.manito_pj.dto.ManitoInfoDTO;

@Controller
public class MainPageController {
    @GetMapping("/ajh.do")
    public String getMethodName(Model model, ManitoInfoDTO roomInfoDTO) {
        System.out.println("주현페이지");
        return "thymeleaf/ajh/index";
    }
}

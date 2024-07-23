package com.leeds.manito.manito_pj.controller.ljh;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.leeds.manito.manito_pj.dto.ManitoInfoDTO;
import com.leeds.manito.manito_pj.service.KakaoService;

import jakarta.servlet.http.HttpSession;



@Controller
public class KakaoController {
    
    @Autowired
    KakaoService kakaoservice;

    @RequestMapping("/kakao/login.do")
    public String kakaoLogin(Model model, ManitoInfoDTO manitoInfoDTO,@RequestParam String code,HttpSession session){
        String accessToken = code;
        System.out.println("어세스코드는 : "+accessToken);
        kakaoservice.test(model);
        accessToken = kakaoservice.getAccessToken(code);
        kakaoservice.getUserInfo(model,accessToken);
        model.addAttribute("at",accessToken);
        model.addAttribute("rUrl","/thym-invite2.do");
        session.setAttribute("at", accessToken);
        return "thymeleaf/ljh/kakao";
    }
    @RequestMapping("/kakao/logout.do")
    public String kakaoLogout(Model model, HttpSession session){
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@여기@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("잠시 :"+session.getAttribute("at"));
        //model.addAttribute("at", session.getAttribute("at"));
        //@RequestParam("accessToken")String accessToken,
        model.addAttribute("rUrl","/ljh.do");
        kakaoservice.kakaoLogout(model);
        //session.invalidate();
        return "thymeleaf/ljh/kakao";
    }
}

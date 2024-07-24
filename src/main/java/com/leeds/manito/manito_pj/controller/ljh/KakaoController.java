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
    KakaoService kakaoService;

    @RequestMapping("/ljh.do")
    public String getMethodName(Model model, ManitoInfoDTO manitoInfoDTO) {
        kakaoService.getSettings(model);
        return "thymeleaf/ljh/main";
    }

    @RequestMapping("/kakao/login2.do")
    public String kakaoLogin(Model model,@RequestParam String code,HttpSession session){
        String accessToken = kakaoService.getAccessToken(code);
        kakaoService.getUserInfo(session,model,accessToken);
        model.addAttribute("rUrl","/thym-makeGame.do");
        return "redirect:/thym-checkLogin.do";
    }
    @RequestMapping("/kakao/logout2.do")
    public String kakaoLogout(Model model, HttpSession session){
        model.addAttribute("rUrl","/ljh.do");
        kakaoService.kakaoLogout(model);
        session.invalidate();
        return "thymeleaf/ljh/kakao";
    }
}

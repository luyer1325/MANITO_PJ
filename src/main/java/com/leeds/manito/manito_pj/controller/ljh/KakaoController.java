package com.leeds.manito.manito_pj.controller.ljh;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.leeds.manito.manito_pj.dto.ManitoInfoDTO;
import com.leeds.manito.manito_pj.dto.UserInfoDTO;
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
    public String kakaoLogin(Model model,UserInfoDTO userInfoDTO,@RequestParam String code,HttpSession session,RedirectAttributes rttr){
        int num = 2;
        String accessToken = kakaoService.getAccessToken(code,2);
        UserInfoDTO user = kakaoService.getUserInfo(session,model,accessToken,userInfoDTO);
        rttr.addFlashAttribute("userInfoDTO",user); //addAttribute --> 파라미터 형식 / addFlashAttribute--> 플래시형식
        //model.addAttribute("rUrl","/thym-makeGame.do");
        return "redirect:/thym-checkLogin.do";
    }
    @RequestMapping("/kakao/logout2.do")
    public String kakaoLogout(Model model, HttpSession session){
        model.addAttribute("rUrl","/ljh.do");
        kakaoService.kakaoLogout(model);
        session.invalidate();
        return "thymeleaf/ljh/kakao";
    }
    @RequestMapping("/kakao/accept.do")
    public String kakaoAccept(Model model,UserInfoDTO userInfoDTO,@RequestParam("code") String code,HttpSession session,RedirectAttributes rttr){
        int num = 3;
        String accessToken = kakaoService.getAccessToken(code,num);
        UserInfoDTO user = kakaoService.getUserInfo(session,model,accessToken,userInfoDTO);
        rttr.addFlashAttribute("userInfoDTO",user); //addAttribute --> 파라미터 형식 / addFlashAttribute--> 플래시형식
        //rttr.addAttribute("idx", idx);
        return "redirect:/thym-accept.do";
    }
}

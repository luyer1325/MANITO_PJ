package com.leeds.manito.manito_pj.controller.ljh;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.leeds.manito.manito_pj.dto.ManitoInfoDTO;
import com.leeds.manito.manito_pj.dto.UserInfoDTO;
import com.leeds.manito.manito_pj.service.KakaoService;
import com.leeds.manito.manito_pj.service.ManitoService;
import com.leeds.manito.manito_pj.service.ManitoService2;
import com.leeds.manito.manito_pj.util.AES;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestMethod;




@Controller
public class ManageController {

    @Autowired
    ManitoService manitoService;

    @Autowired
    ManitoService2 manitoService2;

    @Autowired
    KakaoService kakaoService;

    @Autowired
    AES aes;

    @RequestMapping("/thym-createGame.do")
    public String invite2(Model model, ManitoInfoDTO manitoInfoDTO, @ModelAttribute("at")String at){
        int idx = manitoService.CreateManito(manitoInfoDTO); // 게임 생성
        //kakaoService.getSettings(model);
        //model.addAttribute("logout_redirect_uri", "http://localhost:8080/kakao/logout.do");
        return "thymeleaf/ljh/gameDetail";
    }
    
    @RequestMapping("/thym-ljh.do")
    public String thym(Model model, ManitoInfoDTO manitoInfoDTO, @RequestParam("idx")String encIdx){
        int idx = Integer.parseInt(aes.decrypt_AES(encIdx));
        manitoInfoDTO = manitoService2.getManitoInfo(model,idx);
        manitoService2.insertUser(model, manitoInfoDTO, idx);
        return "thymeleaf/ljh/accept";
    }

    @RequestMapping("/thym-invite.do")
    public String invite(Model model, ManitoInfoDTO manitoInfoDTO){
        model.addAttribute("kakao_redirect_uri", "http://localhost:8080/kakao/login2.do");
        return "thymeleaf/ljh/invite";
    }
    @RequestMapping("/thym-makeGame.do")
    public String makeGame(Model model, ManitoInfoDTO manitoInfoDTO){
        return "thymeleaf/ljh/makeGame";
    }
    @RequestMapping("/thym-checkLogin.do")
    public String checkLogin(Model model, ManitoInfoDTO manitoInfoDTO,HttpSession session){
        UserInfoDTO userInfoDTO = (UserInfoDTO)model.getAttribute("userInfoDTO");

        userInfoDTO.setUserId("imsi@naver.com");// 카카오 초대를 위한 더미 아이디 설정
        session.setAttribute("email", "imsi@naver.com");

        manitoInfoDTO = manitoService.checkLogin(model,userInfoDTO);// 로그인 한 아이디에서 이미 생성된 게임이 있는지 체크하여 url 변경
        System.out.println("@@@@@"+manitoInfoDTO.getManitoIdx()+"@@@@@");
        System.out.println("@@@@@"+manitoInfoDTO.getCreateUser()+"@@@@@");
        model.addAttribute("manitoInfoDTO", manitoInfoDTO);
        model.addAttribute("userInfoDTO", userInfoDTO);
        if(manitoInfoDTO.getCreateUser() == null || manitoInfoDTO.getCreateUser().trim().isEmpty()){
            manitoInfoDTO.setCreateUser("imsi@naver.com");// 카카오 초대를 위한 더미 아이디 설정
            session.setAttribute("email", "imsi@naver.com");
            model.addAttribute("rUrl","/thym-makeGame.do");
        }else {
            model.addAttribute("rUrl","/thym-start.do");
        }
        return "thymeleaf/ljh/kakao2";
    }
    @RequestMapping("/thym-start.do")
    public String startGame(Model model, ManitoInfoDTO manitoInfoDTO ,HttpSession session){
        manitoService.getInfo(model, session);
        model.addAttribute("manitoInfoDTO", manitoInfoDTO);
        String ps = aes.encrypt_AES(String.valueOf(manitoInfoDTO.getManitoIdx()));
        model.addAttribute("encIdx", ps);
        System.out.println("암호화 : "+ps);
        System.out.println("복호화 : "+aes.decrypt_AES(ps));

        
        return "thymeleaf/ljh/start";
    }
    @RequestMapping("/thym-gameDetail.do")
    public String gameDetail(Model model, ManitoInfoDTO manitoInfoDTO, HttpSession session){
        manitoInfoDTO.setCreateUser((String)session.getAttribute("email"));
        int idx = manitoService.CreateManito(manitoInfoDTO);
        manitoService2.insertUser(model,manitoInfoDTO,idx);
        return "thymeleaf/ljh/gameDetail";
    }
    @RequestMapping("/testtt.do")
    public String test(Model model) {
        kakaoService.getSettings(model);
        model.addAttribute("logout_redirect_uri", "http://localhost:8080/kakao/logout.do");
        return "thymeleaf/ljh/gameDetail";
    }
    @RequestMapping("/testttt.do")
    public String test2() {
        
        return "thymeleaf/ljh/test2";
    }
    
    

}
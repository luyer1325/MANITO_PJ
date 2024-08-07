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
        return "thymeleaf/ljh/gameDetail";
    }
    
    @RequestMapping("/thym-ljh.do")
    public String thym(Model model,HttpSession session ,ManitoInfoDTO manitoInfoDTO, @RequestParam("idx")String encIdx){
        kakaoService.getSettings(model);
        int idx = Integer.parseInt(aes.decrypt_AES(encIdx));
        session.setAttribute("idx", idx);
        manitoInfoDTO = manitoService2.getManitoInfo(model,idx);
        model.addAttribute("manitoInfoDTO", manitoInfoDTO);
        //manitoService2.insertUser(model, manitoInfoDTO, idx);
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

        //userInfoDTO.setUserId("imsi@naver.com");// 카카오 초대를 위한 더미 아이디 설정
        //manitoInfoDTO.setCreateUser("imsi@naver.com");
        //session.setAttribute("email", "imsi@naver.com");
        userInfoDTO = manitoService2.checkUser(userInfoDTO); // 날짜를 체크해야함
        manitoInfoDTO = manitoService.checkLogin(userInfoDTO);// 로그인 한 아이디에서 이미 생성된 게임이 있는지 체크하여 url 변경
        if(manitoInfoDTO.getCreateUser() == null && userInfoDTO.getUserId() == null){
            //manitoInfoDTO.setCreateUser("imsi@naver.com");// 카카오 초대를 위한 더미 아이디 설정
            //session.setAttribute("email", "imsi@naver.com");
            model.addAttribute("userInfoDTO", userInfoDTO);
            model.addAttribute("rUrl","/thym-makeGame.do");
        }else {
            model.addAttribute("manitoInfoDTO", manitoInfoDTO);
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

        if(manitoService2.checkCnt((String)session.getAttribute("kakaoId"))== 0){
            manitoService2.insertUser(session,idx);
        }else{
            //manitoService2.updateUser(session,idx);// 회원정보가 있을 시 manitoIdx 변경하기 마니또의 게임이 끝났는지 체크하는 로직 생성 필요
        }
        return "thymeleaf/ljh/gameDetail";
    }
    @RequestMapping("/testtt.do")
    public String test(Model model) {
        kakaoService.getSettings(model);
        model.addAttribute("logout_redirect_uri", "http://localhost:8080/kakao/logout.do");
        return "thymeleaf/ljh/gameDetail";
    }
    @RequestMapping("/thym-accept.do")
    public String accept(Model model,HttpSession session,UserInfoDTO userInfoDTO) {
        int idx = (Integer)session.getAttribute("idx");
        userInfoDTO.setManitoIdx(idx);

        userInfoDTO = manitoService2.checkUser(userInfoDTO); // manitoIdx로 카카오 초대받은 User 정보 검색
        if(userInfoDTO.getKakaoId() == null || userInfoDTO.getKakaoId().trim().isEmpty()){
           userInfoDTO = manitoService2.insertUser(session, idx);
        }
        ManitoInfoDTO manitoInfoDTO = manitoService.checkLogin(userInfoDTO); //User정보로 Manito 정보검색 --> 수정되어야함
        
        model.addAttribute("manitoInfoDTO",manitoInfoDTO);
        model.addAttribute("userInfoDTO", userInfoDTO);
        model.addAttribute("rUrl","/thym-start.do");
        return "thymeleaf/ljh/kakao2";
    }
    
    

}
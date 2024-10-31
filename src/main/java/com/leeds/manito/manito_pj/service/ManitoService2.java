package com.leeds.manito.manito_pj.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.leeds.manito.manito_pj.dto.ManitoInfoDTO;
import com.leeds.manito.manito_pj.dto.UserInfoDTO;
import com.leeds.manito.manito_pj.entity.ManitoInfo;
import com.leeds.manito.manito_pj.entity.UserInfo;
import com.leeds.manito.manito_pj.repository.ManitoRepository;
import com.leeds.manito.manito_pj.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.time.format.DateTimeFormatter;

import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;

@Service
public class ManitoService2 {
    
    @Autowired
    ManitoRepository manitoRepository;

    @Autowired
    UserRepository userRepository;

    ManitoInfo manito = new ManitoInfo();
    UserInfo user = new UserInfo();

    // @Autowired
    // ModelMapper modelMapper;
    private final ModelMapper modelMapper;

    public ManitoService2(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public UserInfoDTO insertUser(HttpSession session,int idx){
        UserInfo ui = UserInfo.builder()
            .manitoIdx(idx)
            .userId((String)session.getAttribute("email"))
            .kakaoId((String)session.getAttribute("kakaoId"))
            .build();
        UserInfo userInfo = userRepository.save(ui); // 저장된 엔티티 반환
        return modelMapper.map(userInfo,UserInfoDTO.class);
    }

    public ManitoInfoDTO getManitoInfo(Model model, int idx){
        ManitoInfo manitoInfo = manitoRepository.findBymanitoIdx(idx).orElseGet(() -> manito);
        return modelMapper.map(manitoInfo, ManitoInfoDTO.class);
    }

    public long checkCnt(String kakaoId){
        return userRepository.countByKakaoId(kakaoId);
    }

    public UserInfoDTO checkUser(UserInfoDTO userInfoDTO){
        UserInfo userInfo= userRepository.findByManitoIdxOrUserId(userInfoDTO.getManitoIdx(),userInfoDTO.getUserId()).orElseGet(()-> user);
        return modelMapper.map(userInfo, UserInfoDTO.class);

    }

    public void startGame(String time){
        List<ManitoInfo> list = manitoRepository.findByStartDate(time);
    }

    public UserInfoDTO checkKakaoId(String kakaoId){
        UserInfo userInfo = userRepository.findByKakaoId(kakaoId).orElseGet(()-> user);
        return modelMapper.map(userInfo,UserInfoDTO.class);
    }

    @Transactional //트랙잭션을 고정하여 한번에 수행하여 쿼리문 중 하나라도 실패시 commit 안함
    public void startGameSetting(Model model,ManitoInfoDTO manitoInfoDTO,UserInfoDTO userInfoDTO){
        manitoInfoDTO = getManitoInfo(model, manitoInfoDTO.getManitoIdx()); // 새로고침 방지
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String now = LocalDateTime.now().format(dtf); // 시간 비교를 위한 객체

        /* 게임시작 세팅 */
        if(now.compareTo(manitoInfoDTO.getStartDate())>0 && manitoInfoDTO.getStatus().equals("B")){// A.compareTo(B) -> A가 B보다 앞이면 음수, 뒤라면 양수 같으면 0을 반환. 즉, now가 startDate보다 날짜가 이후면 양수이므로 game의 status를 'I'로 전환
            manitoRepository.updateStatusByManitoIdx(manitoInfoDTO.getManitoIdx()); //게임 상태 변경
            userRepository.updateStartSetting(manitoInfoDTO.getManitoIdx()); // 게임참가자 닉네임 부여 , 마니또 대상 부여
        }; 

        //타겟 닉네임 불러오기
        String target = userRepository.findNicknameByKakaoId(userInfoDTO.getManitoTarget());
        //내 마니또 불러오기
        String manito = userRepository.findNicknameByManitoTarget(userInfoDTO.getKakaoId());
        List<UserInfo> memberList = userRepository.findByManitoIdx(manitoInfoDTO.getManitoIdx());

        model.addAttribute("memberList", memberList);
        model.addAttribute("target",target);
        model.addAttribute("manito",manito);
        model.addAttribute("manitoInfoDTO", manitoInfoDTO);
        model.addAttribute("userInfoDTO",userInfoDTO);
        
    }

    @Transactional
    public void gameSetting(){
        String abc = "out";
        // int aaa = manitoRepository.setGameSetting();
        // System.out.println("스케쥴러 ="+ aaa);
    }
}

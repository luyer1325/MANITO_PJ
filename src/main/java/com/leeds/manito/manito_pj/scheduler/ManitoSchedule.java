package com.leeds.manito.manito_pj.scheduler;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.leeds.manito.manito_pj.service.ManitoService2;

import java.time.format.DateTimeFormatter;

import lombok.extern.slf4j.Slf4j;

@Slf4j //로그 등록을 위한 추가
@Component // 빈 등록 
public class ManitoSchedule {

        @Autowired
        private ManitoService2 manitoService2;

        @Scheduled (cron = "0 * * * * ?")
        public void checkGame(){
                LocalDateTime now = LocalDateTime.now();
                String time = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                
                System.out.println("스케줄러가 돌아가는 중입니다. ("+ time + ")");
                
                //manitoService2.startGame(time);
                manitoService2.gameSetting();

        }
}

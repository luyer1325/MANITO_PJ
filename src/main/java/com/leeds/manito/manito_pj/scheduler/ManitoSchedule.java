package com.leeds.manito.manito_pj.scheduler;

import java.time.LocalDate;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j     // 로그를 위해서
@Component // 빈 등록 
public class ManitoSchedule {
        @Scheduled (cron = "0 1 * * * ?")
        public void checkGame(){
             LocalDate now = LocalDate.now();
            System.out.println("스케줄러가 돌아가는 중입니다. ("+ now + ")");
        }
}

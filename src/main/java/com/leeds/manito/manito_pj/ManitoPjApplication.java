package com.leeds.manito.manito_pj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// import org.springframework.web.bind.annotation.RestController;
// import org.springframework.web.bind.annotation.GetMapping;


// DB 접속 정보 application.properties 파일에 있으며 해당파일 주석 참고
//@SpringBootApplication(exclude={DataSourceAutoConfiguration.class}) // DB없을 시 주석풀고 위 '@SpringBootApplication' 주석처리
@SpringBootApplication
public class ManitoPjApplication {

	public static void main(String[] args) {
		SpringApplication.run(ManitoPjApplication.class, args);
	}
}

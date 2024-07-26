package com.leeds.manito.manito_pj.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        /*연결 전략 : 같은 타입의 필드명이 같은 경우만 동작*/
		modelMapper.getConfiguration()
        .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE)
        .setFieldMatchingEnabled(true);
		return modelMapper;
    }
}

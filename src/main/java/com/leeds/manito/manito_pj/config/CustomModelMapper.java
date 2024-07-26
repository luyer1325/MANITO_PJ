package com.leeds.manito.manito_pj.config;

import org.modelmapper.ModelMapper;

public class CustomModelMapper extends ModelMapper{
    @Override
    public <D> D map(Object source, Class<D> destinationType) {
        Object tmpSource = source;
        if(source == null){
            tmpSource = new Object();	// 기본 생성자로 생성 처리
        }

        return super.map(tmpSource, destinationType);
    }
}

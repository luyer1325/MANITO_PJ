package com.leeds.manito.manito_pj.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;

import com.leeds.manito.manito_pj.entity.ManitoEntity;
import com.leeds.manito.manito_pj.repository.ManitoRepository;

public class ManitoService {
    private final ManitoRepository manitoRepository;

     @Autowired
    public ManitoService(ManitoRepository manitoRepository) {
        this.manitoRepository = manitoRepository;
    }
    public Optional<ManitoEntity> findByUserName(String username) {
        return manitoRepository.findByUserName(username);
    }
}

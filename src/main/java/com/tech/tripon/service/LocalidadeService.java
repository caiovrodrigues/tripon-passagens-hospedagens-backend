package com.tech.tripon.service;

import com.tech.tripon.domain.entity.Localidade;
import com.tech.tripon.domain.repository.LocalidadeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class LocalidadeService {

    private final LocalidadeRepository locRepo;

    public List<Localidade> getAll(){
        return locRepo.findAll();
    }


}

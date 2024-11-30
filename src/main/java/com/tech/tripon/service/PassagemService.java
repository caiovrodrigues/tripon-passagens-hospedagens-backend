package com.tech.tripon.service;

import com.tech.tripon.domain.entity.Localidade;
import com.tech.tripon.domain.entity.Passagem;
import com.tech.tripon.domain.repository.LocalidadeRepository;
import com.tech.tripon.domain.repository.PassagemRepository;
import com.tech.tripon.web.dto.PassagemEditarDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PassagemService {

    private final PassagemRepository passagemRepository;
    private final LocalidadeRepository localidadeRepository;

    public Passagem saveNovaPassagem(Passagem novaPassagem){
        Localidade origem = localidadeRepository.findById(1).orElseThrow();
        Localidade destino = localidadeRepository.findById(2).orElseThrow();
        Passagem passagem = new Passagem(origem, destino, novaPassagem.getPreco(), novaPassagem.getDataIda(), novaPassagem.getDataVolta(), null, null);
        return passagemRepository.save(passagem);
    }

    public List<Passagem> all() {
        return passagemRepository.findAll();
    }

    public void editar(PassagemEditarDTO passagemDTO) {
        Passagem passagem = passagemRepository.findById(passagemDTO.id()).orElseThrow();
        Localidade origem = localidadeRepository.findByCidade(passagemDTO.origem()).orElseThrow();
        Localidade destino = localidadeRepository.findByCidade(passagemDTO.destino()).orElseThrow();

        passagem.setOrigem(origem);
        passagem.setDestino(destino);
        passagem.setPreco(passagemDTO.preco());

        passagemRepository.save(passagem);
    }

    public void delete(Integer passagemId) {
        passagemRepository.deleteById(passagemId);
    }
}

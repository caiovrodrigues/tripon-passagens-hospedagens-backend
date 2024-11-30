package com.tech.tripon.domain.repository;

import com.tech.tripon.domain.entity.Localidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LocalidadeRepository extends JpaRepository<Localidade, Integer> {

    Optional<Localidade> findByCidade(String cidade);

}

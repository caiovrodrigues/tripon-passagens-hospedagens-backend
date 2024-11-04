package com.tech.tripon.domain.repository;

import com.tech.tripon.domain.entity.Passagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassagemRepository extends JpaRepository<Passagem, Integer> {
}

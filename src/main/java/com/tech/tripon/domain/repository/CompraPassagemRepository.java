package com.tech.tripon.domain.repository;

import com.tech.tripon.domain.entity.CompraPassagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompraPassagemRepository extends JpaRepository<CompraPassagem, Integer> {
}

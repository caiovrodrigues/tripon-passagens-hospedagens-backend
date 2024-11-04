package com.tech.tripon.domain.repository;

import com.tech.tripon.domain.entity.Comodidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComodidadeRepository extends JpaRepository<Comodidade, Integer> {
}

package com.tech.tripon.domain.repository;

import com.tech.tripon.domain.entity.CompanhiaArea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanhiaAereaRepository extends JpaRepository<CompanhiaArea, Integer> {
}

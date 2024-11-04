package com.tech.tripon.domain.repository;

import com.tech.tripon.domain.entity.UsuarioRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRolesRepository extends JpaRepository<UsuarioRoles, Integer> {
}

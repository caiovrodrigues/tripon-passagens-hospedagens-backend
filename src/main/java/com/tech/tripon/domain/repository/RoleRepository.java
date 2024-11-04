package com.tech.tripon.domain.repository;

import com.tech.tripon.domain.entity.Role;
import com.tech.tripon.domain.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
}

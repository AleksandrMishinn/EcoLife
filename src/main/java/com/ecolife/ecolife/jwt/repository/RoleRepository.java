package com.ecolife.ecolife.jwt.repository;

import com.ecolife.ecolife.jwt.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}

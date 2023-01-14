package com.project.warehouse.repository;

import java.util.Optional;

import com.project.warehouse.entity.ERole;
import com.project.warehouse.entity.Role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{
	Optional<Role> findByName(ERole name);
}

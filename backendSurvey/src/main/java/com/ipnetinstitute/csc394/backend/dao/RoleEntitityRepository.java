package com.ipnetinstitute.csc394.backend.dao;

import java.util.Optional;

import javax.transaction.Transactional;

import com.ipnetinstitute.csc394.backend.entity.ERole;
import com.ipnetinstitute.csc394.backend.entity.Role;

import org.springframework.data.jpa.repository.JpaRepository;


@Transactional
public interface RoleEntitityRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(ERole name);
}

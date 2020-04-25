package com.ipnetinstitute.csc394.backend.dao;

import java.util.Optional;

import javax.transaction.Transactional;

import com.ipnetinstitute.csc394.backend.entity.ERole;
import com.ipnetinstitute.csc394.backend.entity.Role;


@Transactional
public interface RoleEntitityRepository extends BaseEntityRepository<Role> {
    Optional<Role> findByName(ERole name);
}

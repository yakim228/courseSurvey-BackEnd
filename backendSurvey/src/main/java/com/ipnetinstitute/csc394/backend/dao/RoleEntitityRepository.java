package com.ipnetinstitute.csc394.backend.dao;

import javax.transaction.Transactional;

import com.ipnetinstitute.csc394.backend.entity.Role;


@Transactional
public interface RoleEntitityRepository extends BaseEntityRepository<Role> {

}

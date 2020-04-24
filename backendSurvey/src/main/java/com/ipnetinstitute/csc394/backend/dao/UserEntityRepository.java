package com.ipnetinstitute.csc394.backend.dao;
import java.util.Optional;

import javax.transaction.Transactional;
import com.ipnetinstitute.csc394.backend.entity.User;

//@NoRepositoryBean
@Transactional
public interface UserEntityRepository extends BaseEntityRepository<User> {


    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}

package com.ipnetinstitute.csc394.backend.dao;
import java.util.Optional;

import javax.transaction.Transactional;
import com.ipnetinstitute.csc394.backend.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;

//@NoRepositoryBean
@Transactional
public interface UserEntityRepository extends BaseEntityRepository<User> {


    Optional<User> findByUserName(String username);

    Boolean existsByUserName(String username);
  
    Boolean existsByEmail(String eMail);
   



}

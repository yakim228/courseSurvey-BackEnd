package com.ipnetinstitute.csc394.backend.security.jwt.services;

import com.ipnetinstitute.csc394.backend.dao.UserEntityRepository;
import com.ipnetinstitute.csc394.backend.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// import com.bezkoder.springjwt.models.User;
// import com.bezkoder.springjwt.repository.UserRepository;

@Service
public class UserDetailsServiceImpl  implements UserDetailsService{

    @Autowired
    UserEntityRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        return UserDetailsImpl.build(user);
   
    }

}
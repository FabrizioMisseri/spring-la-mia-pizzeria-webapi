package org.learning.java.springlamiapizzeriacrud.security;

import jakarta.persistence.Entity;
import org.learning.java.springlamiapizzeriacrud.model.User;
import org.learning.java.springlamiapizzeriacrud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

@Entity
public class DatabaseUserDetailService implements UserDetailsService {
    
    @Autowired
    private UserRepository userRepository;

    @Override

    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            return new DatabaseUserDetails(user.get());
        } else throw new UsernameNotFoundException("Username not found");
    }
}

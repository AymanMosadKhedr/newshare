package com.userManagement.Service;

import com.userManagement.Repository.UserRepository;
import com.userManagement.model.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.userManagement.model.user;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;
@Transactional
@Service
public class UserServiceDetailsImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        user user = userRepository.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException(username);
        }
        Set<GrantedAuthority> authorities = new HashSet<>();
       authorities.add(new SimpleGrantedAuthority(user.getUsername()));

        return new org.springframework.security.core.userdetails.User( user.getUsername(), user.getPassword(), authorities);
    }
    public user UserByUsername(String username) {
        return userRepository.findByUsername(username);

    }


}

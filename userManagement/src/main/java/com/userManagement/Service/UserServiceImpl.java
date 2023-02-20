package com.userManagement.Service;

import com.userManagement.Repository.UserRepository;
import com.userManagement.model.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.security.Principal;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public user saveUser(user user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public user findByUsername(String username){
        return userRepository.findByUsername(username);
    }



    @Override
    public List<user> findAll(){
        return userRepository.findAll();
    }


    @Override
    public void deleteByUsername(String username){

        userRepository.deleteByUsername(username);
    }


    public user Update(user user) {

        return userRepository.save(user);
    }
}

package com.userManagement.Service;

import com.userManagement.Repository.UserRepository;
import com.userManagement.model.user;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.Principal;
import java.util.List;


public interface UserService {


    user saveUser(user user);

    user findByUsername(String username);

    List<user> findAll();


    user Update(user user,long id);

void deleteByUsername (String username);

}

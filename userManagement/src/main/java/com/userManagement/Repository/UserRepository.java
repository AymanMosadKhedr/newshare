package com.userManagement.Repository;
import com.userManagement.model.user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.Optional;

public interface UserRepository extends JpaRepository<user,Long> {


    user findByUsername(String username);

void deleteByUsername (String username);

user Update(user user);
}

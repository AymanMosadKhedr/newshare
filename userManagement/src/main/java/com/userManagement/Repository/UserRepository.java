package com.userManagement.Repository;
import com.userManagement.model.user;
import org.springframework.data.jpa.repository.JpaRepository;

import java.security.Principal;
import java.util.Optional;

public interface UserRepository extends JpaRepository<user,Long> {


    user findByUsername(String username);

void deleteByUsername (String username);
}

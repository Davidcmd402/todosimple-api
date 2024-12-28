package com.davidvieira.todosimple.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.davidvieira.todosimple.models.User;
import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    
    User findByUsername(String username);
}

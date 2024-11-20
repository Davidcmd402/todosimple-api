package com.davidvieira.todosimple.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.davidvieira.todosimple.models.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long>{
    
}

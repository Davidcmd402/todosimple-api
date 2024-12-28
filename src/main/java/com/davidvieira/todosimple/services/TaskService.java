package com.davidvieira.todosimple.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.davidvieira.todosimple.models.Task;
import com.davidvieira.todosimple.models.User;
import com.davidvieira.todosimple.repositories.TaskRepository;
import com.davidvieira.todosimple.services.exceptions.DataBindingViolationException;
import com.davidvieira.todosimple.services.exceptions.ObjectNotFoundException;

@Service
public class TaskService {
    
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserService userService;

    @Transactional
    public Task findById(long id) {
        Optional<Task> task = this.taskRepository.findById(id);
        return task.orElseThrow(()-> new ObjectNotFoundException(
            "Task not found. id: " + ", Type: " + Task.class.getName()
        ));
    }
    
    public List<Task> findAllById(Long userId) {
        List<Task> tasks = this.taskRepository.findByUser_id(userId);
        return tasks;
    }

    @Transactional
    public Task create(Task obj) {
        User user = this.userService.findById(obj.getUser().getId());
        obj.setId(null);
        obj.setUser(user);
        obj = this.taskRepository.save(obj);
        return obj;
    }

    @Transactional
    public Task update(Task obj) {
        Task newobj = findById(obj.getId());
        newobj.setDescription(obj.getDescription());
        return this.taskRepository.save(newobj);
    }
    
    public void delete(Long id) {
        findById(id);
        try {
            this.taskRepository.deleteById(id);
        } catch (Exception e) {
            throw new DataBindingViolationException("Not can delete.");
        }
    }


}

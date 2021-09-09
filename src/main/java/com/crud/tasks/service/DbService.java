package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DbService {

    private final TaskRepository taskRepository;

    public List<Task> getAllTasks(){
        return taskRepository.findAll();
    }

    public Optional<Task> getTask(final long id){
        return taskRepository.findById(id);
    }

    public Task saveTask(final Task task){
        return taskRepository.save(task);
    }

    public void deleteTaskById(final long id){
        taskRepository.deleteById(id);
    }

}

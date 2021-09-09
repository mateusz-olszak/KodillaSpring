package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/task")
@RequiredArgsConstructor
public class TaskController {

    private final DbService service;
    private final TaskMapper taskMapper;

    @GetMapping("/getTasks")
    public List<TaskDto> getTasks(){
        List<Task> tasks = service.getAllTasks();
        return taskMapper.mapToTaskDtoList(tasks);
    }

    @GetMapping("/getTask/{id}")
    public TaskDto getTask(@PathVariable(value = "id") Long id){
        Task task = service.getTask(id);
        return taskMapper.mapToTaskDto(task);
    }

    @DeleteMapping("/deleteTask/{id}")
    public void deleteTask(@PathVariable(value = "id") Long id){

    }

    @PutMapping("/updateTask")
    public TaskDto updateTask(@RequestBody TaskDto taskDto){
        return new TaskDto(1L,"Edited test title","Edited test content");
    }

    @PostMapping("/createTask")
    public void createTask(@RequestBody TaskDto taskDto){

    }

}

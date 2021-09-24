package com.crud.tasks;

import com.crud.tasks.domain.Task;
import com.crud.tasks.service.DbService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TaskTestSuite {

    @Autowired
    private DbService service;

    @Test
    public void test_addTask(){
        Task task = new Task("FixTasks","Couple of things to be fixed");
        service.saveTask(task);
    }

    @Test
    public void test_getAllTasks(){
        List<Task> taskList = service.getAllTasks();

        assertEquals(4,taskList.size());
    }
}

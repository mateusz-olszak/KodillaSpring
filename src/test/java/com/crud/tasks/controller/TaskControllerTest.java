package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringJUnitConfig
@WebMvcTest(TaskController.class)
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DbService dbService;
    @MockBean
    private TaskMapper taskMapper;

    @Test
    void testFetchTasks() throws Exception {
        // Given
        List<Task> tasks = List.of(new Task(1L,"TaskTitle","TaskContent"));
        List<TaskDto> tasksDto = List.of(new TaskDto(1L, "TaskTitle", "TaskContent"));
        when(dbService.getAllTasks()).thenReturn(tasks);
        when(taskMapper.mapToTaskDtoList(tasks)).thenReturn(tasksDto);
        // When Then
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/tasks")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)));
    }

    @Test
    void testFetchTasks_returnsEmptyList() throws Exception {
        // Given
        List<Task> tasks = List.of();
        List<TaskDto> tasksDto = List.of();
        when(dbService.getAllTasks()).thenReturn(tasks);
        when(taskMapper.mapToTaskDtoList(tasks)).thenReturn(tasksDto);
        // When Then
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/tasks")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(0)));
    }

    @Test
    void testFetchTask() throws Exception {
        // Given
        Long id = 1L;
        Task task = new Task(1L, "TaskTitle", "TaskContent");
        TaskDto taskDto = new TaskDto(1L, "TaskTitle", "TaskContent");
        when(dbService.getTask(id)).thenReturn(Optional.of(task));
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);
        // When Then
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/tasks/" + id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id",Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title",Matchers.is("TaskTitle")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content",Matchers.is("TaskContent")));
    }

    @Test
    void testUpdateTask() throws Exception {
        // Given
        Task task = new Task(1L, "TaskTitle", "TaskContent");
        TaskDto taskDto = new TaskDto(1L, "TaskTitle", "TaskContent");
        when(taskMapper.mapToTask(taskDto)).thenReturn(task);
        when(dbService.saveTask(task)).thenReturn(task);
        when(taskMapper.mapToTaskDto(any())).thenReturn(taskDto);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);
        // When Then
        mockMvc.perform(MockMvcRequestBuilders.put("/v1/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                        .andExpect(MockMvcResultMatchers.jsonPath("$.id",Matchers.is(1)))
                        .andExpect(MockMvcResultMatchers.jsonPath("$.title",Matchers.is("TaskTitle")))
                        .andExpect(MockMvcResultMatchers.jsonPath("$.content",Matchers.is("TaskContent")));
    }

    @Test
    void testCreateTask() throws Exception {
        // Given
        Task task = new Task(1L, "TaskTitle", "TaskContent");
        TaskDto taskDto = new TaskDto(1L, "TaskTitle", "TaskContent");
        when(taskMapper.mapToTask(taskDto)).thenReturn(task);
        when(dbService.saveTask(task)).thenReturn(task);
        when(taskMapper.mapToTaskDto(any())).thenReturn(taskDto);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);
        // When Then
        mockMvc.perform(MockMvcRequestBuilders.post("/v1/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id",Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title",Matchers.is("TaskTitle")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content",Matchers.is("TaskContent")));
    }

    @Test
    void testDeleteTask() throws Exception {
        // Given
        Long id = 1L;
        doNothing().when(dbService).deleteTaskById(id);
        // When Then
        mockMvc.perform(MockMvcRequestBuilders.delete("/v1/tasks/" + id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }
}

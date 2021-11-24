package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DbServiceTestSuite {

    @InjectMocks
    private DbService dbService;
    @Mock
    private TaskRepository taskRepository;

    @Test
    void testGetAllTasks() {
        // Given
        List<Task> tasks = List.of(new Task(1L,"TaskTitle","TaskContent"));
        when(taskRepository.findAll()).thenReturn(tasks);
        // When
        List<Task> allTasks = dbService.getAllTasks();
        // Then
        assertEquals(1,allTasks.size());
    }

    @Test
    void testGetTask() {
        // Given
        Task task = new Task(1L,"TaskTitle","TaskContent");
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));
        // When
        Task taskDb = dbService.getTask(1L).get();
        // Then
        assertThat(taskDb).isNotNull();
        assertEquals(1L, taskDb.getId());
    }

    @Test
    void testSaveTask() {
        // Given
        Task task = new Task(1L,"TaskTitle","TaskContent");
        when(taskRepository.save(task)).thenReturn(task);
        // When
        Task taskDb = dbService.saveTask(task);
        // Then
        assertThat(taskDb).isNotNull();
        assertEquals(1L,taskDb.getId());
    }

    @Test
    void testDeleteTask() {
        // Given
        doNothing().when(taskRepository).deleteById(1L);
        // When
        dbService.deleteTaskById(1L);
        // Then
        verify(taskRepository, times(1)).deleteById(1L);
    }
}

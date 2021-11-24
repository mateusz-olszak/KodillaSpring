package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.DbService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TaskControllerTestSuite {

    @InjectMocks
    private TaskController taskController;
    @Mock
    private DbService dbService;
    @Mock
    private TaskMapper taskMapper;

    @Test
    void testFetchBoards() {
        // Given
        List<Task> tasks = List.of(new Task(1L,"TaskTitle","TaskContent"));
        when(dbService.getAllTasks()).thenReturn(tasks);
        when(taskMapper.mapToTaskDtoList(tasks)).thenCallRealMethod();
        when(taskMapper.mapToTaskDto(any())).thenCallRealMethod();
        // When
        List<TaskDto> controllerTasks = taskController.getTasks();
        // Then
        assertEquals(1,controllerTasks.size());
        controllerTasks.forEach(task -> {
            assertThat(task.getId()).isGreaterThan(0);
            assertThat(task.getTitle()).isEqualTo("TaskTitle");
            assertThat(task.getContent()).isEqualTo("TaskContent");
        });
    }

    @Test
    void testGetTask() throws TaskNotFoundException {
        // Given
        Task task = new Task(1L,"TaskTitle","TaskContent");
        when(dbService.getTask(1L)).thenReturn(Optional.of(task));
        when(taskMapper.mapToTaskDto(any())).thenCallRealMethod();
        // When
        TaskDto taskDto = taskController.getTask(1L);
        // Then
        assertThat(taskDto).isNotNull();
        assertEquals(1L, taskDto.getId());
    }

    @Test
    void testCreateTask() {
        // Given
        TaskDto taskDto = new TaskDto(1L, "TaskTitle", "TaskContent");
        Task task = new Task(1L, "TaskTitle", "TaskContent");
        when(taskMapper.mapToTask(taskDto)).thenCallRealMethod();
        when(dbService.saveTask(any())).thenReturn(task);
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);
        // When
        TaskDto controllerTask = taskController.createTask(taskDto);
        // Then
        assertThat(controllerTask).isNotNull();
        assertEquals(TaskDto.class, controllerTask.getClass());
    }

    @Test
    void testUpdateTask() {
        // Given
        TaskDto taskDto = new TaskDto(1L, "TaskTitle", "TaskContent");
        Task task = new Task(1L, "TaskTitle", "TaskContent");
        when(taskMapper.mapToTask(taskDto)).thenCallRealMethod();
        when(dbService.saveTask(any())).thenReturn(task);
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);
        // When
        TaskDto controllerTask = taskController.updateTask(taskDto);
        // Then
        assertThat(controllerTask).isNotNull();
        assertEquals(TaskDto.class, controllerTask.getClass());
    }

    @Test
    void testDeleteTask() {
        // Given
        Long id = 1L;
        doNothing().when(dbService).deleteTaskById(id);
        // When
        taskController.deleteTask(id);
        // Then
        verify(dbService, times(1)).deleteTaskById(id);
    }

    @Test
    void testGetTaskWithWrongId_throwsTaskNotFoundException() {
        // Given
        when(dbService.getTask(1L)).thenThrow(new TaskNotFoundException());
        // When Then
        assertThrows(TaskNotFoundException.class, () -> taskController.getTask(1L));
    }
}

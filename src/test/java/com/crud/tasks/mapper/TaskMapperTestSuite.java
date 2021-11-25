package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TaskMapperTestSuite {
    
    @Mock
    private TaskMapper taskMapper;
    
    @Test
    void testMapToTask() {
        // Given
        TaskDto taskDto = new TaskDto(1L,"TaskTitle","TaskContent");
        when(taskMapper.mapToTask(taskDto)).thenCallRealMethod();
        // When
        Task task = taskMapper.mapToTask(taskDto);
        // Then
        assertThat(task).isNotNull();
        assertEquals(1L, task.getId());
        assertEquals("TaskTitle", task.getTitle());
        assertEquals("TaskContent", task.getContent());
        assertEquals(Task.class, task.getClass());
    }

    @Test
    void testMapToTaskDto() {
        // Given
        Task task = new Task(1L,"TaskTitle","TaskContent");
        when(taskMapper.mapToTaskDto(task)).thenCallRealMethod();
        // When
        TaskDto taskDto = taskMapper.mapToTaskDto(task);
        // Then
        assertThat(taskDto).isNotNull();
        assertEquals(1L, taskDto.getId());
        assertEquals("TaskTitle", taskDto.getTitle());
        assertEquals("TaskContent", taskDto.getContent());
        assertEquals(TaskDto.class, taskDto.getClass());
    }

    @Test
    void testMapToTaskDtoList() {
        // Given
        List<Task> tasks = List.of(new Task(1L,"TaskTitle","TaskContent"));
        when(taskMapper.mapToTaskDtoList(tasks)).thenCallRealMethod();
        when(taskMapper.mapToTaskDto(any())).thenCallRealMethod();
        // When
        List<TaskDto> tasksDto = taskMapper.mapToTaskDtoList(tasks);
        // Then
        assertThat(tasksDto).isNotNull();
        assertThat(tasksDto.size()).isEqualTo(1);
        assertEquals(1L, tasksDto.get(0).getId());
        assertEquals("TaskTitle", tasksDto.get(0).getTitle());
        assertEquals("TaskContent", tasksDto.get(0).getContent());
        assertEquals(TaskDto.class, tasksDto.get(0).getClass());
    }
}

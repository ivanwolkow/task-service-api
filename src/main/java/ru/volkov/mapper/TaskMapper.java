package ru.volkov.mapper;

import ru.volkov.dto.TaskDto;
import ru.volkov.entity.TaskEntity;

public class TaskMapper {

    public static TaskDto map(TaskEntity taskEntity) {
        return new TaskDto(taskEntity.getStatus(), taskEntity.getUpdated());
    }
}

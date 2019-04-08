package ru.volkov.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import ru.volkov.entity.TaskStatus;

import java.time.OffsetDateTime;

@ApiModel("Some simple task")
public class TaskDto {

    @ApiModelProperty(name = "Task status", allowableValues = "created, running, finished")
    private TaskStatus status;
    @ApiModelProperty(name = "The task update time")
    private OffsetDateTime timestamp;

    public TaskDto() {
    }

    public TaskDto(TaskStatus status, OffsetDateTime timestamp) {
        this.status = status;
        this.timestamp = timestamp;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public OffsetDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(OffsetDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "TaskDto{" +
                "status=" + status +
                ", timestamp=" + timestamp +
                '}';
    }
}

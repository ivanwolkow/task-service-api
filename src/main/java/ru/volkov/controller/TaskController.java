package ru.volkov.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;
import ru.volkov.dto.TaskDto;
import ru.volkov.service.TaskService;

import java.util.UUID;

@RestController
@RequestMapping("/task")
@CrossOrigin
@Api("This is a simple task controller")
public class TaskController {

    private TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    @ApiOperation("Create a new task")
    public ResponseEntity<UUID> createTask() {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(taskService.createTask());
    }

    @GetMapping("/{id}")
    @ApiOperation("Get a task status by the task id")
    public TaskDto getTaskById(@PathVariable("id") String uuid) {
        return taskService.getTaskById(uuid);
    }

    @GetMapping("/poll/{id}")
    @ApiOperation("Get a task status by the task id (long-polling)")
    public DeferredResult<TaskDto> longPollTaskById(@PathVariable("id") String uuid) {
        return taskService.pollTaskById(uuid);
    }
}

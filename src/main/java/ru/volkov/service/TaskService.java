package ru.volkov.service;

import com.google.common.util.concurrent.Uninterruptibles;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.async.DeferredResult;
import ru.volkov.dto.TaskDto;
import ru.volkov.entity.TaskEntity;
import ru.volkov.entity.TaskStatus;
import ru.volkov.exceptions.TaskNotFoundException;
import ru.volkov.exceptions.WrongUUIDException;
import ru.volkov.mapper.TaskMapper;
import ru.volkov.repository.TaskRepository;

import javax.annotation.PostConstruct;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Service
public class TaskService {

    private TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    private ExecutorService executorService;

    @PostConstruct
    public void init() {
        executorService = Executors.newCachedThreadPool();
    }

    public UUID createTask() {
        TaskEntity savedTask = taskRepository.save(new TaskEntity());
        CompletableFuture.runAsync(() -> runTask(savedTask), executorService);
        return savedTask.getUuid();
    }

    public TaskDto getTaskById(String id) {
        UUID uuid;
        try {
            uuid = UUID.fromString(id);
        } catch (IllegalArgumentException e) {
            throw new WrongUUIDException();
        }

        return taskRepository.findById(uuid)
                .map(TaskMapper::map)
                .orElseThrow(TaskNotFoundException::new);
    }

    public DeferredResult<TaskDto> pollTaskById(String id) {
        DeferredResult<TaskDto> deferredResult = new DeferredResult<>();

        CompletableFuture.runAsync(() -> {
            try {
                TaskDto taskDto = getTaskById(id);
                deferredResult.setResult(taskDto);
            } catch (Exception e) {
                deferredResult.setErrorResult(e);
            }

        }, executorService);

        return deferredResult;
    }

    private void runTask(TaskEntity taskEntity) {
        taskEntity.setStatus(TaskStatus.RUNNING);
        taskEntity = taskRepository.save(taskEntity);

        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.MINUTES);

        taskEntity.setStatus(TaskStatus.FINISHED);
        taskRepository.save(taskEntity);
    }
}

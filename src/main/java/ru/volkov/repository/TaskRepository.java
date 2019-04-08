package ru.volkov.repository;

import org.springframework.data.repository.CrudRepository;
import ru.volkov.entity.TaskEntity;

import java.util.UUID;

public interface TaskRepository extends CrudRepository<TaskEntity, UUID> {

}

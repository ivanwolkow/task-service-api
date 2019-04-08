package ru.volkov.entity;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "tasks")
public class TaskEntity {

    @Id
    private UUID uuid;

    private OffsetDateTime updated;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    public TaskEntity() {
    }

    public TaskEntity(UUID uuid) {
        this.uuid = uuid;
    }

    @PrePersist
    public void prePersist() {
        this.uuid = UUID.randomUUID();
        this.status = TaskStatus.CREATED;
        this.updated = OffsetDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.updated = OffsetDateTime.now();
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public OffsetDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(OffsetDateTime updated) {
        this.updated = updated;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "TaskEntity{" +
                "uuid=" + uuid +
                ", updated=" + updated +
                ", status=" + status +
                '}';
    }
}

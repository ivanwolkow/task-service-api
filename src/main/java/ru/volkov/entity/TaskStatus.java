package ru.volkov.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum TaskStatus {
    @JsonProperty("created")
    CREATED,
    @JsonProperty("running")
    RUNNING,
    @JsonProperty("finished")
    FINISHED
}

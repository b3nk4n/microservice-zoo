package de.benkan.data.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record Message(@Positive @JsonProperty("userId") long userId,
                      @NotBlank @JsonProperty("channel") String channel,
                      @NotBlank @JsonProperty("content") String content) { }

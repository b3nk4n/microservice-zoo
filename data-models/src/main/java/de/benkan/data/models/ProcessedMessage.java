package de.benkan.data.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.Set;

public record ProcessedMessage(@Positive @JsonProperty("userId") long userId,
                               @NotBlank @JsonProperty("channel") String channel,
                               @NotBlank @JsonProperty("content") String content,
                               @JsonProperty("badLanguage") boolean badLanguage,
                               @NotNull @JsonProperty("tags") Set<Tag> tags) { }

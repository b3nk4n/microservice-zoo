package de.benkan.shared.kafka;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

public record KafkaConfig(@NotBlank @JsonProperty("bootstrapServer") String bootstrapServer,
                          @NotBlank @JsonProperty("acks") String acks,
                          @PositiveOrZero @JsonProperty("retries") int retries,
                          @PositiveOrZero @JsonProperty("lingerMs") long lingerMs) { }

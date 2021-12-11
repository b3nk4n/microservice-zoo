package de.benkan.shared.kafka.producer;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

public record KafkaProducerConfig(@NotBlank @JsonProperty("bootstrapServer") String bootstrapServer,
                                  @NotBlank @JsonProperty("topic") String topic,
                                  @NotBlank @JsonProperty("acks") String acks,
                                  @PositiveOrZero @JsonProperty("retries") int retries,
                                  @PositiveOrZero @JsonProperty("lingerMs") long lingerMs) { }

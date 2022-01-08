package de.benkan.shared.kafka.consumer;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record KafkaConsumerConfig(@NotBlank @JsonProperty("bootstrapServer") String bootstrapServer,
                                  @NotBlank @JsonProperty("topic") String topic,
                                  @NotBlank @JsonProperty("groupId") String groupId,
                                  @NotBlank @JsonProperty("keyDeserializer") String keyDeserializer,
                                  @NotBlank @JsonProperty("valueDeserializer") String valueDeserializer,
                                  @Positive @JsonProperty("pollTimeoutMs") long pollTimeoutMs) { }

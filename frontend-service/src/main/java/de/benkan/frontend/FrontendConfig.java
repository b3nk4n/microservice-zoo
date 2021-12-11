package de.benkan.frontend;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.benkan.shared.kafka.KafkaConfig;
import io.dropwizard.Configuration;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class FrontendConfig extends Configuration {
    @NotNull
    @Valid
    private final KafkaConfig kafkaConfig;

    public FrontendConfig(@JsonProperty("kafka") KafkaConfig kafkaConfig) {
        this.kafkaConfig = kafkaConfig;
    }

    public KafkaConfig getKafkaConfig() {
        return kafkaConfig;
    }
}

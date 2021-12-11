package de.benkan.frontend;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.benkan.shared.kafka.producer.KafkaProducerConfig;
import io.dropwizard.Configuration;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class FrontendConfig extends Configuration {
    @NotNull
    @Valid
    private final KafkaProducerConfig kafkaProducerConfig;

    public FrontendConfig(@JsonProperty("kafkaProducer") KafkaProducerConfig kafkaProducerConfig) {
        this.kafkaProducerConfig = kafkaProducerConfig;
    }

    public KafkaProducerConfig getKafkaProducerConfig() {
        return kafkaProducerConfig;
    }
}

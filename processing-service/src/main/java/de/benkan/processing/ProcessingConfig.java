package de.benkan.processing;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.benkan.shared.kafka.consumer.KafkaConsumerConfig;
import io.dropwizard.Configuration;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class ProcessingConfig extends Configuration {
    @NotNull
    @Valid
    private final KafkaConsumerConfig kafkaConsumerConfig;

    public ProcessingConfig(@JsonProperty("kafkaConsumer") KafkaConsumerConfig kafkaConsumerConfig) {
        this.kafkaConsumerConfig = kafkaConsumerConfig;
    }

    public KafkaConsumerConfig getKafkaConsumerConfig() {
        return kafkaConsumerConfig;
    }
}

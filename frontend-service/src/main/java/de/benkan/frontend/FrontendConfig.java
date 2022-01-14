package de.benkan.frontend;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.benkan.settings.client.SettingsClientConfig;
import de.benkan.shared.kafka.producer.KafkaProducerConfig;
import io.dropwizard.Configuration;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class FrontendConfig extends Configuration {
    @NotNull
    @Valid
    private final KafkaProducerConfig kafkaProducerConfig;

    @NotNull
    @Valid
    private final SettingsClientConfig settingsClientConfig;

    public FrontendConfig(@JsonProperty("kafkaProducer") KafkaProducerConfig kafkaProducerConfig,
                          @JsonProperty("settingsClientConfig") SettingsClientConfig settingsClientConfig) {
        this.kafkaProducerConfig = kafkaProducerConfig;
        this.settingsClientConfig = settingsClientConfig;
    }

    public KafkaProducerConfig getKafkaProducerConfig() {
        return kafkaProducerConfig;
    }

    public SettingsClientConfig getSettingsClientConfig() {
        return settingsClientConfig;
    }
}

package de.benkan.processing;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import de.benkan.data.models.Message;
import de.benkan.shared.kafka.consumer.KafkaConsumerConfig;
import de.benkan.shared.kafka.consumer.KafkaReader;
import de.benkan.shared.kafka.consumer.ReactiveKafkaReader;

public class ProcessingModule extends AbstractModule {
    private final ProcessingConfig processingConfig;

    public ProcessingModule(ProcessingConfig processingConfig) {
        this.processingConfig = processingConfig;
    }

    @Override
    protected void configure() {
        // configs
        bind(KafkaConsumerConfig.class).toInstance(processingConfig.getKafkaConsumerConfig());
    }

    @Provides
    @Singleton
    private KafkaReader<Message> messageKafkaReader(KafkaConsumerConfig config) {
        return new KafkaReader<>(config);
    }

    @Provides
    @Singleton
    private ReactiveKafkaReader<Message> reactiveMessageKafkaReader(KafkaReader<Message> kafkaReader) {
        return new ReactiveKafkaReader<>(kafkaReader);
    }
}

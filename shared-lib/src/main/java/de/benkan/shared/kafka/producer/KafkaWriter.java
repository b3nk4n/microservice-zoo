package de.benkan.shared.kafka.producer;

import de.benkan.shared.kafka.serialization.JsonSerializer;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.Properties;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class KafkaWriter<V> {
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaWriter.class);

    private final Producer<String, V> producer;
    private final KafkaProducerConfig config;

    @Inject
    public KafkaWriter(KafkaProducerConfig config) {
        this.config = config;
        this.producer = createProducer(config);
    }

    public void write(String key, V value) {
        Future<RecordMetadata> sendFuture = producer.send(new ProducerRecord<>(config.topic(), key, value));

        try {
            RecordMetadata metadata = sendFuture.get(1, TimeUnit.SECONDS);
            LOGGER.info("Message sent {} to {}", value, metadata);
        } catch (InterruptedException e) {
            LOGGER.warn("Send feature got interrupted");
            // Restore interrupted state...
            Thread.currentThread().interrupt();
        } catch (Exception e) {
            LOGGER.error("Could not write message", e);
        }
    }

    public void close() {
        producer.close();
    }

    private KafkaProducer<String, V> createProducer(KafkaProducerConfig config) {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, config.bootstrapServer());
        props.put(ProducerConfig.ACKS_CONFIG, config.acks());
        props.put(ProducerConfig.RETRIES_CONFIG, config.retries());
        props.put(ProducerConfig.LINGER_MS_CONFIG, config.lingerMs());
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new KafkaProducer<>(props);
    }
}

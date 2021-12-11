package de.benkan.shared.kafka.producer;

import de.benkan.shared.kafka.serialization.JsonSerializer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import javax.inject.Inject;
import java.util.Properties;

public class KafkaWriter<V> {
    private final Producer<String, V> producer;
    private final KafkaProducerConfig config;

    @Inject
    public KafkaWriter(KafkaProducerConfig config) {
        this.config = config;
        this.producer = createProducer(config);
    }

    public void write(String key, V value) {
        producer.send(new ProducerRecord<>(config.topic(), key, value));
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

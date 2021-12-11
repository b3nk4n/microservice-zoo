package de.benkan.shared.kafka;

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

    @Inject
    public KafkaWriter(KafkaConfig config) {
        Properties props = createProperties(config);
        producer = new KafkaProducer<>(props);
    }

    public void send(String topic, String key, V value) {
        producer.send(new ProducerRecord<>(topic, key, value));
    }

    public void close() {
        producer.close();
    }

    private Properties createProperties(KafkaConfig config) {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, config.bootstrapServer());
        props.put(ProducerConfig.ACKS_CONFIG, config.acks());
        props.put(ProducerConfig.RETRIES_CONFIG, config.retries());
        props.put(ProducerConfig.LINGER_MS_CONFIG, config.lingerMs());
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return props;
    }
}

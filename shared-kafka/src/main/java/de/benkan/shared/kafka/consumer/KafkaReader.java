package de.benkan.shared.kafka.consumer;

import com.google.common.collect.Streams;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRebalanceListener;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Closeable;
import java.time.Duration;
import java.util.Collection;
import java.util.Properties;
import java.util.Set;

public class KafkaReader<V> implements ConsumerRebalanceListener, Closeable {
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaReader.class);

    private final String topic;
    private final Duration pollTimeout;
    private final KafkaConsumer<String, V> kafkaConsumer;

    public KafkaReader(KafkaConsumerConfig config) {
        this.kafkaConsumer = createConsumer(config);
        this.topic = config.topic();
        this.pollTimeout = Duration.ofMillis(config.pollTimeoutMs());
    }

    public Collection<V> poll() {
        Iterable<ConsumerRecord<String, V>> records = kafkaConsumer
                .poll(pollTimeout)
                .records(topic);
        return Streams.stream(records)
                .map(ConsumerRecord::value)
                .toList();
    }

    @Override
    public void close() {
        kafkaConsumer.close();
    }

    @Override
    public void onPartitionsRevoked(Collection<TopicPartition> partitions) {
        LOGGER.info("Kafka revoked {} partitions: {}", partitions.size(), partitionIds(partitions));
    }

    @Override
    public void onPartitionsAssigned(Collection<TopicPartition> partitions) {
        LOGGER.info("Kafka assigned {} partitions: {}", partitions.size(), partitionIds(partitions));
    }

    private Collection<Integer> partitionIds(Collection<TopicPartition> partitions) {
        return partitions.stream()
                .map(TopicPartition::partition)
                .toList();
    }

    private KafkaConsumer<String, V> createConsumer(KafkaConsumerConfig config) {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, config.bootstrapServer());
        props.put(ConsumerConfig.GROUP_ID_CONFIG, config.groupId());
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, config.keyDeserializer());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, config.valueDeserializer());
        KafkaConsumer<String, V> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Set.of(config.topic()), this);
        return consumer;
    }

    public String getTopic() {
        return topic;
    }
}

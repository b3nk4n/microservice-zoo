package de.benkan.shared.kafka.consumer;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.io.Closeable;

public class ReactiveKafkaReader<V> implements Closeable {
    private final KafkaReader<V> kafkaReader;
    private Flux<V> flux;

    public ReactiveKafkaReader(KafkaReader<V> kafkaReader) {
        this.kafkaReader = kafkaReader;
    }

    public void start() {
        Scheduler scheduler = Schedulers.newSingle(String.join(".", kafkaReader.getTopic(), "subscriber"));
        flux = Flux.create(sink ->
                sink.onRequest(requested ->
                        scheduler.schedule(() -> {
                            try {
                                for (int i = 0; i < requested && !sink.isCancelled(); ++i) {
                                    kafkaReader.poll().forEach(sink::next);
                                }
                            } catch (Exception e) {
                                sink.error(e);
                            }
                        })));
    }

    @Override
    public void close() {
        kafkaReader.close();
    }

    public Flux<V> getFlux() {
        return flux;
    }
}

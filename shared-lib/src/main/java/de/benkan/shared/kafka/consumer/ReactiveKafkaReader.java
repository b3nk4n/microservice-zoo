package de.benkan.shared.kafka.consumer;

import reactor.core.Disposable;
import reactor.core.Disposables;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.io.Closeable;

public class ReactiveKafkaReader<V> implements Closeable {
    private final KafkaReader<V> kafkaReader;
    private final Disposable.Composite disposables = Disposables.composite();
    private Flux<V> flux;

    public ReactiveKafkaReader(KafkaReader<V> kafkaReader) {
        this.kafkaReader = kafkaReader;
    }

    public void start() {
        Scheduler scheduler = Schedulers.newSingle(String.join(".", kafkaReader.getTopic(), "subscriber"));
        flux = Flux.create(sink ->
                sink.onRequest(requested ->
                        disposables.add(
                                scheduler.schedule(() -> {
                                    try {
                                        for (int i = 0; i < requested && !sink.isCancelled(); ++i) {
                                            kafkaReader.poll()
                                                    .forEach(sink::next);
                                        }
                                    } catch (Exception e) {
                                        sink.error(e);
                                    }
                                })
                        )
                )
        );
    }

    @Override
    public void close() {
        kafkaReader.close();
        disposables.dispose();
    }

    public Flux<V> getFlux() {
        return flux;
    }
}

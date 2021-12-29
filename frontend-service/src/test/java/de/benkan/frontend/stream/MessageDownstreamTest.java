package de.benkan.frontend.stream;

import de.benkan.data.models.Message;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;

class MessageDownstreamTest {

    private MessageDownstream sut;

    @BeforeEach
    void setUp() {
        sut = new MessageDownstream();
    }

    @Test
    void emitMessage() {
        Message testMessage = new Message(1, "channel", "Test content");

        boolean result = sut.emit(testMessage);
        assertThat(result).isTrue();

        StepVerifier.create(sut.getFlux())
                .expectSubscription()
                .expectNext(testMessage)
                .verifyTimeout(Duration.ofSeconds(1L));
    }
}
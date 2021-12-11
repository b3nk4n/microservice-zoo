package de.benkan.frontend;

import com.google.inject.AbstractModule;
import de.benkan.frontend.services.MessageService;
import de.benkan.frontend.services.MessageServiceImpl;
import de.benkan.shared.kafka.producer.KafkaProducerConfig;

public class FrontendModule extends AbstractModule {
    private final FrontendConfig frontendConfig;

    public FrontendModule(FrontendConfig frontendConfig) {
        this.frontendConfig = frontendConfig;
    }

    @Override
    protected void configure() {
        // configs
        bind(KafkaProducerConfig.class).toInstance(frontendConfig.getKafkaProducerConfig());

        // services
        bind(MessageService.class).to(MessageServiceImpl.class);
    }
}

package de.benkan.frontend;

import com.google.inject.AbstractModule;
import de.benkan.frontend.services.MessageService;
import de.benkan.frontend.services.MessageServiceImpl;
import de.benkan.shared.kafka.KafkaConfig;

public class FrontendModule extends AbstractModule {
    private final FrontendConfig frontendConfig;

    public FrontendModule(FrontendConfig frontendConfig) {
        this.frontendConfig = frontendConfig;
    }

    @Override
    protected void configure() {
        // configs
        bind(KafkaConfig.class).toInstance(frontendConfig.getKafkaConfig());

        // services
        bind(MessageService.class).to(MessageServiceImpl.class);
    }
}

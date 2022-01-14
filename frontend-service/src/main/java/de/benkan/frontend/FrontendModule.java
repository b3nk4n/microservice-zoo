package de.benkan.frontend;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import de.benkan.frontend.services.MessageService;
import de.benkan.frontend.services.MessageServiceImpl;
import de.benkan.settings.client.SettingsClient;
import de.benkan.shared.kafka.producer.KafkaProducerConfig;

public class FrontendModule extends AbstractModule {
    private final FrontendConfig config;

    public FrontendModule(FrontendConfig config) {
        this.config = config;
    }

    @Override
    protected void configure() {
        // configs
        bind(KafkaProducerConfig.class).toInstance(config.getKafkaProducerConfig());

        // services
        bind(MessageService.class).to(MessageServiceImpl.class);
    }

    @Provides
    @Singleton
    SettingsClient settingsClient() {
        return new SettingsClient(config.getSettingsClientConfig());
    }
}

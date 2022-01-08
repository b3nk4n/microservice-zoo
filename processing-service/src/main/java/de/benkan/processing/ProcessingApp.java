package de.benkan.processing;

import com.google.inject.Guice;
import com.google.inject.Injector;
import de.benkan.processing.health.PingHealthCheck;
import de.benkan.processing.stream.MessageProcessingStream;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

public class ProcessingApp extends Application<ProcessingConfig> {
    @Override
    public String getName() {
        return "processing-service";
    }

    @Override
    public void run(ProcessingConfig configuration, Environment environment) {
        Injector injector = Guice.createInjector(new ProcessingModule(configuration));

        registerServices(environment, injector);
        registerHealthChecks(environment);
    }

    private void registerServices(Environment environment, Injector injector) {
        environment.lifecycle().manage(injector.getInstance(MessageProcessingStream.class));
    }

    private void registerHealthChecks(Environment environment) {
        environment.healthChecks().register("ping", new PingHealthCheck());
    }

    public static void main(String[] args) throws Exception {
        new ProcessingApp().run(args);
    }
}

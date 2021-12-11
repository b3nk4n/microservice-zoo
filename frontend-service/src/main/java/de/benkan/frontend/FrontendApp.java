package de.benkan.frontend;

import com.google.inject.Guice;
import com.google.inject.Injector;
import de.benkan.frontend.health.PingHealthCheck;
import de.benkan.frontend.resources.MessageResource;
import de.benkan.frontend.stream.MessageStream;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

public class FrontendApp extends Application<FrontendConfig> {
    @Override
    public String getName() {
        return "frontend-service";
    }

    @Override
    public void run(FrontendConfig configuration, Environment environment) {
        Injector injector = Guice.createInjector(new FrontendModule(configuration));

        registerResources(environment, injector);
        registerServices(environment, injector);
        registerHealthChecks(environment);
    }

    private void registerResources(Environment environment, Injector injector) {
        environment.jersey().register(injector.getInstance(MessageResource.class));
    }

    private void registerServices(Environment environment, Injector injector) {
        environment.lifecycle().manage(injector.getInstance(MessageStream.class));
    }

    private void registerHealthChecks(Environment environment) {
        environment.healthChecks().register("ping", new PingHealthCheck());
    }

    public static void main(String[] args) throws Exception {
        new FrontendApp().run(args);
    }
}

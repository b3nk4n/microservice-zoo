package de.benkan.processing.health;

import com.codahale.metrics.health.HealthCheck;

import javax.inject.Singleton;

@Singleton
public class PingHealthCheck extends HealthCheck {
    @Override
    protected Result check() {
        return Result.healthy();
    }
}

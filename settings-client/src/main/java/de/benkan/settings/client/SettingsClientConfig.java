package de.benkan.settings.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record SettingsClientConfig (
        @JsonProperty("hostName") @NotBlank String hostName,
        @JsonProperty("port") @Positive int port,
        @JsonProperty("basePath") @NotNull String basePath) {
}

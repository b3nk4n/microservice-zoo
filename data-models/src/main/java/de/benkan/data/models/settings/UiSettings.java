package de.benkan.data.models.settings;

import jakarta.validation.constraints.Positive;

public record UiSettings(@Positive boolean darkMode) {
}

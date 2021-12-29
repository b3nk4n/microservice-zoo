package de.benkan.data.models;

import jakarta.validation.constraints.Positive;

public record Settings(@Positive int parallelism) {
}

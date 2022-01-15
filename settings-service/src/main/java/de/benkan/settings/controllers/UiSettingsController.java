package de.benkan.settings.controllers;

import de.benkan.data.models.settings.UiSettings;
import de.benkan.settings.entities.UiSettingsEntity;
import de.benkan.settings.repositories.UiSettingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("settings")
public class UiSettingsController {

    private static final UiSettings DEFAULT_SETTINGS = new UiSettings(false);

    private final UiSettingsRepository repository;

    @Autowired
    public UiSettingsController(UiSettingsRepository repository) {
        this.repository = repository;
    }

    @GetMapping()
    public UiSettings getIndex() {
        Iterable<UiSettingsEntity> settingsEntityIterable = repository
                .findAll();

        UiSettingsEntity firstSettingsEntityOrNull = settingsEntityIterable.iterator().hasNext()
                ? settingsEntityIterable.iterator().next()
                : null;

        return Optional.ofNullable(firstSettingsEntityOrNull)
                .map(this::fromEntity)
                .orElse(DEFAULT_SETTINGS);
    }

    @PostMapping()
    public UiSettings postIndex(@RequestBody UiSettings uiSettings) {
        UiSettingsEntity entity = new UiSettingsEntity(uiSettings.darkMode());
        UiSettingsEntity savedEntity = repository.save(entity);
        return fromEntity(savedEntity);
    }

    private UiSettings fromEntity(UiSettingsEntity entity) {
        return new UiSettings(entity.isDarkMode());
    }

}

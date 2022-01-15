package de.benkan.settings.repositories;

import de.benkan.settings.entities.UiSettingsEntity;
import org.springframework.data.repository.CrudRepository;

public interface UiSettingsRepository extends CrudRepository<UiSettingsEntity, Long> {
}

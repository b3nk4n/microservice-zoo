package de.benkan.settings.repositories;

import de.benkan.settings.Application;
import de.benkan.settings.entities.UiSettingsEntity;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = Application.class)
@Transactional
class UiSettingsRepositoryTest {
    @Resource
    private UiSettingsRepository repository;

    @Test
    void findAllWhenEmpty() {
        Iterable<UiSettingsEntity> result = repository.findAll();

        assertThat(result).isEmpty();
    }

    @Test
    void saveAndFind() {
        UiSettingsEntity entity = new UiSettingsEntity(true);
        UiSettingsEntity savedEntity = repository.save(entity);

        Iterable<UiSettingsEntity> result = repository.findAll();
        assertThat(result)
                .hasSize(1)
                .first()
                .isEqualTo(savedEntity);
    }
}
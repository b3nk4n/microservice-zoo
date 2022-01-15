package de.benkan.settings.entities;

import javax.persistence.*;

@Entity
@Table(name = "ui_settings")
public class UiSettingsEntity {

    protected UiSettingsEntity() {
        // required empty constructor for Entity
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "darkMode")
    private boolean darkMode;

    public UiSettingsEntity (boolean darkMode) {
        this.darkMode = darkMode;
    }

    public boolean isDarkMode() {
        return darkMode;
    }

}

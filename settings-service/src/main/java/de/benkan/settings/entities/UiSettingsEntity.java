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

    public long getId() {
        return id;
    }

    public boolean isDarkMode() {
        return darkMode;
    }

    public void setDarkMode(boolean darkMode) {
        this.darkMode = darkMode;
    }
}

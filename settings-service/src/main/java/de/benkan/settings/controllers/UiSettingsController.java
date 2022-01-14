package de.benkan.settings.controllers;

import de.benkan.data.models.settings.UiSettings;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("settings")
public class UiSettingsController {

    @GetMapping()
    public UiSettings index() {
        return new UiSettings(true);
    }

}

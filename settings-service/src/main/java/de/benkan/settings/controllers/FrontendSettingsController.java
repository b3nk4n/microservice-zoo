package de.benkan.settings.controllers;

import de.benkan.data.models.Settings;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FrontendSettingsController {

    @GetMapping("/")
    public Settings index() {
        return new Settings(2);
    }

}

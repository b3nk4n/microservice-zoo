package de.benkan.frontend.resources;

import de.benkan.data.models.Settings;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Singleton
@Path("/settings")
@Produces(MediaType.APPLICATION_JSON)
public class SettingsResource {
    @GET
    public Settings getSettings() {
        // TODO forward call to settings service
        return new Settings(2);
    }
}

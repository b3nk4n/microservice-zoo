package de.benkan.frontend.resources;

import de.benkan.data.models.settings.UiSettings;
import de.benkan.settings.client.SettingsClient;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Singleton
@Path("/settings")
@Produces(MediaType.APPLICATION_JSON)
public class SettingsResource {

    private final SettingsClient settingsClient;

    @Inject
    public SettingsResource(SettingsClient settingsClient) {
        this.settingsClient = settingsClient;
    }

    @GET
    public UiSettings getSettings() {
        return settingsClient.getSettingsSync()
                .orElseThrow(() -> new WebApplicationException(Response.Status.SERVICE_UNAVAILABLE));
    }

    @POST
    public UiSettings postSettings(@NotNull @Valid UiSettings uiSettings) {
        return settingsClient.postSettingsSync(uiSettings)
                .orElseThrow(() -> new WebApplicationException(Response.Status.SERVICE_UNAVAILABLE));
    }

}

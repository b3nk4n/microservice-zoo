package de.benkan.frontend.resources;

import de.benkan.data.models.Message;
import de.benkan.frontend.services.MessageService;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Singleton
@Path("/message")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MessageResource {
    private final MessageService messageService;

    @Inject
    public MessageResource(MessageService messageService) {
        this.messageService = messageService;
    }

    @POST
    public Response postMessage(@NotNull @Valid Message message) {
        if (messageService.acceptMessage(message)) {
            return Response.ok()
                    .entity(Entity.json(message))
                    .build();
        }

        return Response.status(Response.Status.BAD_REQUEST)
                .entity(Entity.text("Text"))
                .build();
    }
}

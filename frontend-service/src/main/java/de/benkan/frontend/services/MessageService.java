package de.benkan.frontend.services;

import de.benkan.data.models.Message;

public interface MessageService {
    boolean acceptMessage(Message message);
}

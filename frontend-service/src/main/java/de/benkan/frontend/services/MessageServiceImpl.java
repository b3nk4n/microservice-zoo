package de.benkan.frontend.services;

import de.benkan.data.models.Message;
import de.benkan.frontend.stream.MessageDownstream;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class MessageServiceImpl implements MessageService {
    private final MessageDownstream messageDownstream;

    @Inject
    public MessageServiceImpl(MessageDownstream messageDownstream) {
        this.messageDownstream = messageDownstream;
    }

    @Override
    public boolean acceptMessage(Message message) {
        return messageDownstream.emit(message);
    }
}

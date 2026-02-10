package hr.fer.seminarbackend.handler;

import hr.fer.seminarbackend.model.ClientMessage;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@Component
public class MessageHandlerRegistry {
    private final Map<ClientMessage.MessageType, MessageHandler> map =
            new EnumMap<>(ClientMessage.MessageType.class);

    public MessageHandlerRegistry(List<MessageHandler> handlers) {
        for (MessageHandler h : handlers) {
            var prev = map.put(h.supports(), h);
            if (prev != null) throw new IllegalStateException("Duplicate handler for handler" + h.supports());
        }
    }

    public MessageHandler get(ClientMessage.MessageType type) {
        return map.get(type);
    }
}

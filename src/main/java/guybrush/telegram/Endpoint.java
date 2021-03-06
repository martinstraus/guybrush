package guybrush.telegram;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import guybrush.nlp.*;
import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST endpoint for integration with Telegram.
 *
 * @author Martín Straus <martinstraus@gmail.com>
 */
@RestController("TelegramEndpoint")
@RequestMapping("/telegram")
public class Endpoint {

    private final Bot bot;
    private final Updates updates;
    private final ObjectMapper objectMapper;
    private final NaturalLanguageProcessor nlp;

    public Endpoint(Bot bot, Updates updates, ObjectMapper objectMapper,
        NaturalLanguageProcessor nlp) {
        this.bot = bot;
        this.updates = updates;
        this.objectMapper = objectMapper;
        this.nlp = nlp;
    }

    @Transactional
    @PostMapping("/updates")
    public void update(@RequestBody String payload) throws JsonProcessingException {
        var update = objectMapper.readValue(payload, Update.class);
        if (update.getMessage() == null) {
            return;
        }
        updates.store(update.getUpdate_id(), payload);
        var message = update.getMessage();
        var chat = message.getChat();

        message.process(messageCallback());
    }

    private Message.Callback messageCallback() {
        return new Message.Callback() {
            @Override
            public void process(Chat chat, String message, Optional<User> from) {
                var callback = intentionCallback(chat, message, from);
                var intention = nlp.interpret(message);
                intention.handle(callback);
            }
        };
    }

    private Intention.Callback intentionCallback(Chat chat, String message, Optional<User> from) {
        return new Intention.Callback() {
            public void salutation() {
                bot.send(from.get(), "¡Hola!");
            }

            public void reminders() {
                bot.dailyEvent();
            }

            public void unknown() {
                bot.send(from.get(), "No te entiendo.");
            }
        };
    }

}

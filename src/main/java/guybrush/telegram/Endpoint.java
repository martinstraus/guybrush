package guybrush.telegram;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import guybrush.collections.Reductions;
import static guybrush.collections.Sets.*;
import guybrush.nlp.Intention;
import guybrush.nlp.NaturalLanguageProcessor;
import guybrush.reminders.Reminder;
import guybrush.reminders.Reminders;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import static java.util.stream.Collectors.joining;
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

    private final List<Reminders> reminders;
    private final Bot bot;
    private final Updates updates;
    private final ObjectMapper objectMapper;
    private final NaturalLanguageProcessor nlp;

    public Endpoint(List<Reminders> reminders, Bot bot, Updates updates, ObjectMapper objectMapper,
        NaturalLanguageProcessor nlp) {
        this.reminders = reminders;
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
        message.process(new Message.Callback() {
            @Override
            public void process(Chat chat, String message, Optional<User> from) {
                var intention = nlp.interpret(message);
                switch (intention) {
                    case SALUTATION:
                        bot.send(from.get(), "¡Hola!");
                        break;
                    case UNKNOWN:
                    default:
                        bot.send(
                            from.get(),
                            String.format(
                                "Received message \"%s\" from %s",
                                message,
                                from.get().getUsername()
                            )
                        );
                }
            }
        });
    }

    @PostMapping("/reminders/process")
    public void processReminders() {
        bot.send(messages(remindersForToday()));
    }

    private Set<Reminder> remindersForToday() {
        return union(Reductions.toSet(reminders, Reminders::forToday));
    }

    private String messages(Set<Reminder> reminders) {
        return reminders.stream().map(Reminder::message).collect(joining("\n"));
    }

}

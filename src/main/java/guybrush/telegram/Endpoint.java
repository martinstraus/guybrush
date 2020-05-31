package guybrush.telegram;

import guybrush.collections.Reductions;
import static guybrush.collections.Sets.*;
import guybrush.reminders.Reminder;
import guybrush.reminders.Reminders;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import static java.util.stream.Collectors.joining;
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

    public Endpoint(List<Reminders> reminders, Bot bot) {
        this.reminders = reminders;
        this.bot = bot;
    }

    @PostMapping("/updates")
    public void update(@RequestBody Update update) {
        if (update.getMessage() == null) {
            return;
        }
        var message = update.getMessage();
        var chat = message.getChat();
        message.process(new Message.Callback() {
            @Override
            public void process(Chat chat, String message, Optional<User> from) {
                bot.send(from.get(), message);
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

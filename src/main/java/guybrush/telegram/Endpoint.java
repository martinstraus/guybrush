package guybrush.telegram;

import guybrush.reminders.Reminder;
import guybrush.reminders.Reminders;
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

    private final Reminders reminders;
    private final Telegram telegram;

    public Endpoint(Reminders reminders, Telegram telegram) {
        this.reminders = reminders;
        this.telegram = telegram;
    }

    @PostMapping("/updates")
    public void update(@RequestBody String raw, @RequestBody Update update) {
        if (update.getMessage() != null) {
            var chat = update.getMessage().getChat();
        }

    }

    @PostMapping("/reminders/process")
    public void processReminders() {
        var message = reminders
                .forToday()
                .stream()
                .map(Reminder::message)
                .collect(joining("\n"));
        telegram.send("@martinstraus", message);
    }

}

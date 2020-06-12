package guybrush.telegram;

import guybrush.reminders.Reminder;
import guybrush.reminders.Reminders;
import java.util.Set;
import static java.util.stream.Collectors.joining;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 *
 * @author Martín Straus <martinstraus@gmail.com>
 */
@Component
public class Bot {

    private final Reminders reminders;
    private final Telegram telegram;
    private final String username;

    public Bot(
        Reminders reminders,
        Telegram telegram,
        @Value("${guybrush.telegram.recipient_username}") String username
    ) {
        this.reminders = reminders;
        this.telegram = telegram;
        this.username = username;
    }

    @Scheduled(cron = "0 0 9 * * 1-5")
    public void weekDayEvent() {
        telegram.send(username, "¡Buen día!");
    }

    @Scheduled(cron = "0 0 9 * * *")
    public void dailyEvent() {
        telegram.send(username, messages(reminders.forToday()));
    }

    private String messages(Set<Reminder> reminders) {
        return reminders.stream().map(Reminder::message).collect(joining("\n"));
    }

    public void send(String message) {
        telegram.send(username, message);
    }

    public void send(User to, String message) {
        telegram.send(to.getUsername(), message);
    }
}

package guybrush.telegram;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 *
 * @author Martín Straus <martinstraus@gmail.com>
 */
@Component
public class Bot {

    private final Telegram telegram;
    private final String username;

    public Bot(
            Telegram telegram, 
            @Value("${guybrush.telegram.recipient_username}") String username
    ) {
        this.telegram = telegram;
        this.username = username;
    }

    @Scheduled(cron = "0 0 9 * * 1-5")
    public void dailyEvent() {
        telegram.send(username, "¡Buen día!");
    }
    
    public void send(String message) {
        telegram.send(username, message);
    }

}

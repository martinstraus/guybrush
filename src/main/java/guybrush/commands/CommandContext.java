package guybrush.commands;

import guybrush.telegram.Bot;
import guybrush.telegram.Telegram;
import org.springframework.stereotype.Component;

/**
 *
 * @author Martín Straus
 */
@Component
public class CommandContext {

    private final Telegram telegram;
    private final Bot bot;

    public CommandContext(Telegram telegram, Bot bot) {
        this.telegram = telegram;
        this.bot = bot;
    }

    public Telegram telegram() {
        return telegram;
    }

    public Bot bot() {
        return bot;
    }

}

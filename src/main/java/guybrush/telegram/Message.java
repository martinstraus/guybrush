package guybrush.telegram;

import java.util.Optional;

/**
 *
 * @author Martín Straus <martinstraus@gmail.com>
 * @see https://core.telegram.org/bots/api#message
 */
@lombok.Data
public class Message {

    public static interface Callback {

        void process(Chat chat, String message, Optional<User> from);
    }

    private int message_id;
    private User from;
    private int date;
    private Chat chat;
    private String text;

    public void process(Callback callback) {
        if (text != null) {
            callback.process(chat, text, Optional.ofNullable(from));
        }
    }
}

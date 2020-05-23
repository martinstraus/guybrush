package guybrush.api.telegram;

/**
 *
 * @author Martín Straus <martinstraus@gmail.com>
 * @see https://core.telegram.org/bots/api#message
 */
@lombok.Data
public class Message {

    private Integer message_id;
    private User from;
    private int date;

}

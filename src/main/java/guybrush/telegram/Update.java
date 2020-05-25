package guybrush.telegram;

/**
 *
 * @author Martín Straus <martinstraus@gmail.com>
 * @see https://core.telegram.org/bots/api#update
 */
@lombok.Data
public class Update {

    private Integer update_id;
    private Message message;
    private Message edited_message;
    private Message channel_post;
    private Message edited_channel_post;

}

package guybrush.api.telegram;

/**
 * @author Martín Straus <martinstraus@gmail.com>
 */
public class SendMessageRequest {

    private Object chat_id;
    private String text;
    private String parse_mode;
    private Integer reply_to_message_id;
    
}

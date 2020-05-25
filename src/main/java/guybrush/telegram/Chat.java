package guybrush.telegram;

/**
 *
 * @author Martín Straus <martinstraus@gmail.com>
 * @see https://core.telegram.org/bots/api#chat
 */
@lombok.Data
public class Chat {
    private long id;
    private String type;
    private String title;
    private String username;
    private String first_name;
    private String last_name;
}

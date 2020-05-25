package guybrush.telegram;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 *
 * @author Martín Straus <martinstraus@gmail.com>
 */
@Component
public class RESTTelegram implements Telegram {

    private static final String SEND_MESSAGE_TEMPLATE = """
                                                        {
                                                            "chat_id": %d,
                                                            "text": "%s"
                                                        }
                                                        """;
    private final String apiKey;
    private final long recipientChatId;

    public RESTTelegram(
            @Value("${guybrush.telegram.api_key}") String apiKey,
            @Value("${guybrush.telegram.recipient_chat_id}") long recipientChatId
    ) {
        this.apiKey = apiKey;
        this.recipientChatId = recipientChatId;
    }

    @Override
    public void send(String username, String message) {
        var payload = String.format(SEND_MESSAGE_TEMPLATE, recipientChatId, message);
        var client = ClientBuilder.newClient();
        Response response = client
                .target("https://api.telegram.org/bot" + apiKey + "/sendMessage")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.json(payload));
    }

}

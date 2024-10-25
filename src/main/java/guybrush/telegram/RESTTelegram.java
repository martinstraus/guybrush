package guybrush.telegram;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.time.Duration;
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
    private final HttpClient httpClient;
    private final String apiKey;
    private final long recipientChatId;
    private final URI sendMessageURI;

    public RESTTelegram(
            @Value("${guybrush.telegram.api_key}") String apiKey,
            @Value("${guybrush.telegram.recipient_chat_id}") long recipientChatId
    ) {
        this.apiKey = apiKey;
        this.recipientChatId = recipientChatId;
        try {
            this.sendMessageURI = new URI("https://api.telegram.org/bot" + apiKey + "/sendMessage");
        } catch (URISyntaxException ex) {
            throw new RuntimeException(ex);
        }
        this.httpClient = HttpClient
                .newBuilder()
                .connectTimeout(Duration.ofSeconds(1))
                .build();
    }

    @Override
    public void send(String username, String message) {
        var payload = String.format(SEND_MESSAGE_TEMPLATE, recipientChatId, message);
        try {
            httpClient.send(sendMessageRequesr(payload), HttpResponse.BodyHandlers.discarding());
        } catch (IOException | InterruptedException ex) {
            throw new RuntimeException(ex);
        }
    }

    private HttpRequest sendMessageRequesr(String payload) {
        return HttpRequest.newBuilder()
                .uri(sendMessageURI)
                .header("Content-type", "application/json")
                .POST(BodyPublishers.ofString(payload))
                .build();
    }

}

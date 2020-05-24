package guybrush.api.telegram;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST endpoint for integration with Telegram.
 *
 * @author Martín Straus <martinstraus@gmail.com>
 */
@RestController("TelegramEndpoint")
@RequestMapping("/telegram")
public class Endpoint {
    
    private final Callback callback;
    
    public Endpoint(Callback callback) {
        this.callback = callback;
    }
    
    @PostMapping("/updates")
    public void update(@RequestBody Update update) {
        callback.updateReceived(update.getUpdate_id());
    }
    
}

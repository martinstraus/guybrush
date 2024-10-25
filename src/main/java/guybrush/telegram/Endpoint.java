package guybrush.telegram;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import guybrush.commands.CommandContext;
import guybrush.nlp.*;
import java.util.Optional;
import java.util.logging.Logger;
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
    
    private static final Logger LOGGER = Logger.getLogger(Endpoint.class.getName());
    
    private final ObjectMapper objectMapper;
    private final NaturalLanguageProcessor nlp;
    private final CommandContext commandContext;
    
    public Endpoint(ObjectMapper objectMapper, NaturalLanguageProcessor nlp, CommandContext commandContext) {
        this.objectMapper = objectMapper;
        this.nlp = nlp;
        this.commandContext = commandContext;
    }
    
    @PostMapping("/updates")
    public void update(@RequestBody String payload) throws JsonProcessingException {
        var update = objectMapper.readValue(payload, Update.class);
        if (update.getMessage() == null) {
            return;
        }
        update.getMessage().process(messageCallback());
    }
    
    private Message.Callback messageCallback() {
        return new Message.Callback() {
            @Override
            public void process(Chat chat, String message, Optional<User> from) {
                nlp.interpret(message).command().execute(commandContext, from);
            }
        };
    }
    
}

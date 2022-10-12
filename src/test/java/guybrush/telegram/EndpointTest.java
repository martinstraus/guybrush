package guybrush.telegram;

import java.util.Random;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

/**
 *
 * @author Martín Straus <martinstraus@gmail.com>
 */
@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@AutoConfigureMockMvc
public class EndpointTest {

    private static final String UPDATE_TEMPLATE = """
                                                  "update_id": %s
                                                  """;

    @Autowired
    private WebApplicationContext wac;

    @Mock
    private Callback callback;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    @Disabled
    public void updateReceivesMessage() throws Exception {
        var random = new Random();
        var updateId = random.nextInt();
        var update = String.format(UPDATE_TEMPLATE, updateId);
        mockMvc.perform(post("/telegram/updates").content(update));
        verify(callback, times(1)).updateReceived(updateId);
    }
}

package guybrush.health;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Martín Straus <martinstraus@gmail.com>
 */
@RestController("HealthEndpoint")
public class Endpoint {
    
    @GetMapping(value="/ping", produces = "text/plain")
    public String ping() {
        return "pong";
    }
    
}

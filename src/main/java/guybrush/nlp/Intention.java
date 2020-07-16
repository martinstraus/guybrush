package guybrush.nlp;

import static java.util.Arrays.asList;
import java.util.Set;

/**
 *
 * @author Martín Straus <martinstraus@gmail.com>
 */
public enum Intention {
    SALUTATION {
        @Override
        public boolean matches(String message) {
            return !asList(message.split(" |¡|!|¿|\\?|\\.|;|,"))
            .stream()
            .map(String::toLowerCase)
            .filter((s) ->  Set.of("hola", "buen día", "buenas noches").contains(s))
            .findAny()
            .isEmpty();
        }
    },
    REMINDERS {
        @Override
        public boolean matches(String message) {
            return message.equalsIgnoreCase("recordatorios");
        }
    }, 
    UNKNOWN {
        @Override
        public boolean matches(String message) {
            return true;
        }
    };
    
    public abstract boolean matches(String message);
}

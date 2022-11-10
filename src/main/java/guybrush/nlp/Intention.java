package guybrush.nlp;

import guybrush.commands.Command;
import guybrush.commands.QueryReminders;
import guybrush.commands.Salutation;
import guybrush.commands.Unknown;
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
                .filter((s) -> Set.of("hola", "buen día", "buenas noches").contains(s))
                .findAny()
                .isEmpty();
        }

        @Override
        public Command command() {
            return new Salutation();
        }
    },
    REMINDERS {
        @Override
        public boolean matches(String message) {
            return message.equalsIgnoreCase("recordatorios");
        }

        @Override
        public Command command() {
            return new QueryReminders();
        }
    },
    UNKNOWN {
        @Override
        public boolean matches(String message) {
            return true;
        }

        @Override
        public Command command() {
            return new Unknown();
        }
    };

    public abstract boolean matches(String message);

    public abstract Command command();
}

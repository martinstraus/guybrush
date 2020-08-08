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
                .filter((s) -> Set.of("hola", "buen día", "buenas noches").contains(s))
                .findAny()
                .isEmpty();
        }

        @Override
        public void handle(Callback callback) {
            callback.salutation();
        }
    },
    REMINDERS {
        @Override
        public boolean matches(String message) {
            return message.equalsIgnoreCase("recordatorios");
        }

        @Override
        public void handle(Callback callback) {
            callback.reminders();
        }
    },
    UNKNOWN {
        @Override
        public boolean matches(String message) {
            return true;
        }

        @Override
        public void handle(Callback callback) {
            callback.unknown();
        }
    };

    public static interface Callback {

        void salutation();

        void reminders();

        void unknown();
    }

    public abstract boolean matches(String message);

    public abstract void handle(Callback callback);
}

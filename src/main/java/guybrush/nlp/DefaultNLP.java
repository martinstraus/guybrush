package guybrush.nlp;

import static java.util.Arrays.asList;
import java.util.Set;
import org.springframework.stereotype.Component;

/**
 *
 * @author Martín Straus <martinstraus@gmail.com>
 */
@Component
public class DefaultNLP implements NaturalLanguageProcessor {

    private final Set<String> salutations = Set.of("hola", "buen día", "buenas noches");

    @Override
    public Intention interpret(String message) {
        var isSalutation = !asList(message.split(" |¡|!|¿|\\?|\\.|;|,"))
            .stream()
            .map(String::toLowerCase)
            .filter((s) -> salutations.contains(s))
            .findAny()
            .isEmpty();
        return isSalutation ? Intention.SALUTATION : Intention.UNKNOWN;
    }

}

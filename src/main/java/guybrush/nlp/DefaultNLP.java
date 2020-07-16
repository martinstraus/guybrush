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

    @Override
    public Intention interpret(String message) {
        return asList(Intention.values())
            .stream()
            .filter((intention) -> intention.matches(message))
            .findFirst()
            .orElse(Intention.UNKNOWN);
    }

}

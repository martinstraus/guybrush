package guybrush.nlp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Martín Straus <martinstraus@gmail.com>
 */
public class DefaultNLPTest {

    @Test
    public void intentReturnsExpectedValues() {
        var nlp = new DefaultNLP();
        assertEquals(Intention.SALUTATION, nlp.interpret("¡Hola!"));
    }
}

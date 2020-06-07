package guybrush.nlp;

/**
 *
 * @author Martín Straus <martinstraus@gmail.com>
 */
public interface NaturalLanguageProcessor {

    Intention interpret(String message);
}

package guybrush.collections;

import java.util.Collection;
import java.util.Set;
import java.util.function.Function;
import static java.util.stream.Collectors.toSet;

/**
 *
 * @author Martín Straus <martinstraus@gmail.com>
 */
public class Collections {
    
    public static <T, U> Set<U> collectSet(Collection<T> collection, Function<T, U> function) {
        return collection.stream().map(function).collect(toSet());
    }
    
}

package guybrush.collections;

import java.util.Collection;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 *
 * @author Martín Straus <martinstraus@gmail.com>
 */
public class Reductions {

    public static <T, U> Set<U> toSet(Collection<T> collection, Function<T, U> function) {
        return collection.stream().map(function).collect(Collectors.toSet());
    }
}

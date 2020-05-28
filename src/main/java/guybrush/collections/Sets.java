package guybrush.collections;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Martín Straus <martinstraus@gmail.com>
 */
public class Sets {

    public static <T> Set<T> union(Collection<Set<T>> sets) {
        Set<T> union = new HashSet<>();
        for (Set<T> s : sets) {
            union.addAll(s);
        }
        return union;
    }

}

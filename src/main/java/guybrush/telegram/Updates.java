package guybrush.telegram;

/**
 * Repository of updates.
 * @author Martín Straus <martinstraus@gmail.com>
 */
public interface Updates {
    
    void store(long id, String payload);
    
}

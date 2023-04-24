package guybrush.reminders;

import java.util.Set;

/**
 *
 * @author Martín Straus <martinstraus@gmail.com>
 */
public interface Reminders {
    
    Set<Reminder> forToday();
    
    Set<Reminder> all();
}

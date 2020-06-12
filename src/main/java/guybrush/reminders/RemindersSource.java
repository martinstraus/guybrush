package guybrush.reminders;

import java.util.Set;

/**
 *
 * @author Martín Straus <martinstraus@gmail.com>
 */
public interface RemindersSource {

    Set<Reminder> forToday();
}

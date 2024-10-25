package guybrush.reminders;

import java.time.LocalDate;

/**
 *
 * @author Martín Straus <martinstraus@gmail.com>
 */
public interface Reminder {

    String message();

    boolean isForDate(LocalDate date);
}

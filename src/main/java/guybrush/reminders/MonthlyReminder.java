package guybrush.reminders;

import java.time.LocalDate;

/**
 * A periodic that's activated once a month, a given day.
 *
 * @author Martín Straus <martinstraus@gmail.com>
 */
public class MonthlyReminder implements Reminder {

    private final int dayOfMonth;
    private final String message;

    public MonthlyReminder(int dayOfMonth, String message) {
        this.dayOfMonth = dayOfMonth;
        this.message = message;
    }

    @Override
    public String message() {
        return message;
    }
    
    @Override
    public boolean isForDate(LocalDate date) {
        return date.getDayOfMonth() == dayOfMonth;
    }

}

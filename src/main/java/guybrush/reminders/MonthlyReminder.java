package guybrush.reminders;

/**
 * A periodic that's activated once a month, a given day.
 *
 * @author Martín Straus <martinstraus@gmail.com>
 */
public class MonthlyReminder implements Reminder {

    private final String message;

    public MonthlyReminder(String message) {
        this.message = message;
    }

    @Override
    public String message() {
        return message;
    }

}

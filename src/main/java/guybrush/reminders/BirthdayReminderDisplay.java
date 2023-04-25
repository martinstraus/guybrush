package guybrush.reminders;

import guybrush.contacts.Contact;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Martín Straus <martinstraus@gmail.com>
 */
public class BirthdayReminderDisplay implements Reminder {

    private static final DateTimeFormatter MONTH_DAY_FORMATTER = DateTimeFormatter.ofPattern("dd/MM");
    private final Contact contact;
    
    public BirthdayReminderDisplay(Contact contact) {
        this.contact = contact;
    }
    
    @Override
    public String message() {
        return String.format("%1$s cumple años el dia %2$s", contact.name(), MONTH_DAY_FORMATTER.format(contact.birthday()));
    }
    
}

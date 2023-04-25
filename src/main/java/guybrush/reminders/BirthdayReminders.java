package guybrush.reminders;

import guybrush.contacts.Contacts;
import java.time.LocalDate;
import java.util.Set;
import static java.util.stream.Collectors.toSet;
import org.springframework.stereotype.Component;

/**
 *
 * @author Martín Straus <martinstraus@gmail.com>
 */
@Component
public class BirthdayReminders implements RemindersSource {

    private final Contacts contacts;

    public BirthdayReminders(Contacts contacts) {
        this.contacts = contacts;
    }

    @Override
    public Set<Reminder> forToday() {
        return contacts
                .bornOn(LocalDate.now())
                .stream()
                .map((contact) -> new BirthdayReminder(contact))
                .collect(toSet());
    }

    @Override
    public Set<Reminder> all() {
        return contacts.all().stream().map(BirthdayReminderDisplay::new).collect(toSet());
    }

}

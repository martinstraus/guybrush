package guybrush.reminders;

import guybrush.contacts.Contact;

/**
 *
 * @author Martín Straus <martinstraus@gmail.com>
 */
public class BirthdayReminder implements Reminder {

    private final Contact contact;

    public BirthdayReminder(Contact contact) {
        this.contact = contact;
    }

    @Override
    public String message() {
        return String.format("Hoy cumple años %s", contact.name());
    }

}

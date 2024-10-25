package guybrush.contacts;

import java.time.LocalDate;
import java.time.MonthDay;

/**
 *
 * @author Martín Straus <martinstraus@gmail.com>
 */
public class SimpleContact implements Contact {

    private final String name;
    private final MonthDay birthday;

    public SimpleContact(String name, MonthDay birthday) {
        this.name = name;
        this.birthday = birthday;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public MonthDay birthday() {
        return birthday;
    }

    @Override
    public boolean isBirthday(LocalDate date) {
        return birthday.getDayOfMonth() == date.getDayOfMonth() && birthday.getMonth() == date.getMonth();
    }

}

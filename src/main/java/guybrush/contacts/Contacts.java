package guybrush.contacts;

import java.time.LocalDate;
import java.util.Set;

/**
 *
 * @author Martín Straus <martinstraus@gmail.com>
 */
public interface Contacts {

    Set<Contact> bornOn(LocalDate date);
    
}

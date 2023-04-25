package guybrush.contacts;

import java.time.MonthDay;

/**
 *
 * @author Martín Straus <martinstraus@gmail.com>
 */
public interface Contact {

    String name();
    
    MonthDay birthday();
}

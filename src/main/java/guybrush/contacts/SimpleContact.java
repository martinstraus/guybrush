package guybrush.contacts;

/**
 *
 * @author Martín Straus <martinstraus@gmail.com>
 */
public class SimpleContact implements Contact {

    private final String name;

    public SimpleContact(String name) {
        this.name = name;
    }

    @Override
    public String name() {
        return name;
    }

}

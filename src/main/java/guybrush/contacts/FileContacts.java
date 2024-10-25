package guybrush.contacts;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.MonthDay;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import static java.util.stream.Collectors.toSet;
import org.springframework.stereotype.Component;

@Component
public class FileContacts implements Contacts {

    private static final DateTimeFormatter MONTH_DAY_FORMAT = DateTimeFormatter.ofPattern("MM/dd");
    private final Path path;
    private final Set<Contact> contacts;
    public FileContacts() throws IOException {
        this.path = Paths.get("/var/guybrush/martinstraus.dat");
        List<String> lines = Files.readAllLines(path);
        this.contacts = parse(lines);
    }
    
    private Set<Contact> parse(List<String> lines) {
        Set<Contact> contacts = new HashSet<>();
        for (String line : lines) {
            line = line.trim();
            if (line.isBlank() || line.startsWith("#")) {
                continue;
            }
            String[] parts = line.split(";");
            if (parts.length < 2) {
                continue;
            }
            String name = parts[0].trim();
            MonthDay birthday = MonthDay.parse(parts[1].trim(), MONTH_DAY_FORMAT);
            contacts.add(new SimpleContact(name, birthday));
        }
        return contacts;
    }

    @Override
    public Set<Contact> bornOn(LocalDate date) {
        return contacts.stream().filter((c) -> c.isBirthday(date)).collect(toSet());
    }

    @Override
    public Set<Contact> all() {
        return contacts;
    }

}

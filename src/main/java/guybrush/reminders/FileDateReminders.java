package guybrush.reminders;

import guybrush.db.Queries;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import static java.util.stream.Collectors.toSet;
import org.springframework.stereotype.Component;

@Component
public class FileDateReminders implements RemindersSource {

    private final Path file;
    private final Set<Reminder> monthlyReminders;

    public FileDateReminders() throws IOException {
        this.file = Paths.get("/var/guybrush/monthly_reminders.dat");
        this.monthlyReminders = parse(file);
    }
    
    private Set<Reminder> parse(Path file) throws IOException {
        Set<Reminder> reminders = new HashSet<>();
        List<String> lines = Files.readAllLines(file);
        for (String line : lines) {
            line = line.trim();
            if (line.isBlank() || line.startsWith("#")) {
                continue;
            }
            String[] parts = line.split(";");
            if (parts.length < 2) {
                continue;
            }
            int dayInMonth = Integer.parseInt(parts[0].trim());
            String message = parts[1].trim();
            reminders.add(new MonthlyReminder(dayInMonth, message));
        }
        return reminders;
    }

    @Override
    public Set<Reminder> forToday() {
        var today = LocalDate.now();
        return monthlyReminders.stream().filter((r) -> r.isForDate(today)).collect(toSet());
    }

    @Override
    public Set<Reminder> all() {
        return monthlyReminders;
    }

}

package guybrush.reminders;

import guybrush.collections.Reductions;
import static guybrush.collections.Sets.union;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Component;

/**
 *
 * @author Martín Straus <martinstraus@gmail.com>
 */
@Component
public class CompositeReminders implements Reminders {

    private final List<RemindersSource> reminders;

    public CompositeReminders(List<RemindersSource> reminders) {
        this.reminders = reminders;
    }

    @Override
    public Set<Reminder> forToday() {
        return union(Reductions.toSet(reminders, RemindersSource::forToday));
    }

}

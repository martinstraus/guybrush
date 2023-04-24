package guybrush.reminders;

import guybrush.db.Queries;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Set;
import javax.sql.DataSource;
import org.springframework.stereotype.Component;

/**
 *
 * @author Martín Straus <martinstraus@gmail.com>
 */
@Component
public class DateReminders implements RemindersSource {

    private static final String SELECT_FOR_TODAY = "select *"
            + " from date_reminders"
            + " where (date is null and day_in_month = ?) or (date = ? and day_in_month is null)";
    private static final String SELECT_ALL = "select * from date_reminders";
    private final DataSource dataSource;

    public DateReminders(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Set<Reminder> forToday() {
        var today = LocalDate.now();
        return Queries.selectSet(
                dataSource,
                SELECT_FOR_TODAY,
                this::transformOne,
                today.getDayOfMonth(),
                today
        );
    }

    @Override
    public Set<Reminder> all() {
        return Queries.selectSet(dataSource, SELECT_ALL, this::transformOne);
    }

    private MonthlyReminder transformOne(ResultSet resultSet) {
        try {
            return new MonthlyReminder(resultSet.getString("message"));
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

}

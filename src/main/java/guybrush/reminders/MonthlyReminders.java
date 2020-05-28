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
public class MonthlyReminders implements Reminders {

    private static final String SELECT_FOR_TODAY = "select *"
            + " from monthly_reminders"
            + " where day_in_month = ?";
    private final DataSource dataSource;

    public MonthlyReminders(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Set<Reminder> forToday() {
        return Queries.selectSet(
                dataSource,
                SELECT_FOR_TODAY,
                this::transformOne,
                LocalDate.now().getDayOfMonth()
        );
    }

    private MonthlyReminder transformOne(ResultSet resultSet) {
        try {
            return new MonthlyReminder(resultSet.getString("message"));
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

}

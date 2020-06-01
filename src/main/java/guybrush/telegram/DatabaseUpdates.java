package guybrush.telegram;

import guybrush.db.Queries;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import javax.sql.DataSource;
import org.springframework.stereotype.Component;

/**
 *
 * @author Martín Straus <martinstraus@gmail.com>
 */
@Component
public class DatabaseUpdates implements Updates {

    private final DataSource dataSource;

    public DatabaseUpdates(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void store(long id, String payload) {
        try {
            Queries.insert(
                dataSource,
                "insert into telegram_updates (update_id, reception_timestamp, update_payload) values (?,?,?::jsonb)",
                id,
                Timestamp.valueOf(LocalDateTime.now()),
                payload
            );
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

}

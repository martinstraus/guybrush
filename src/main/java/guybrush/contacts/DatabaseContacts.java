package guybrush.contacts;

import guybrush.db.Queries;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;
import javax.sql.DataSource;
import org.springframework.stereotype.Component;

/**
 *
 * @author Martín Straus <martinstraus@gmail.com>
 */
@Component
public class DatabaseContacts implements Contacts {

    private final DataSource datasource;

    public DatabaseContacts(DataSource datasource) {
        this.datasource = datasource;
    }

    @Override
    public Set<Contact> bornOn(LocalDate date) {
        return Queries.selectSet(
                datasource,
                "select * from contacts where birthday = ?",
                this::transformOne,
                DateTimeFormatter.ofPattern("MM/dd").format(date)
        );
    }

    @Override
    public Set<Contact> all() {
        return Queries.selectSet(
                datasource,
                "select * from contacts",
                this::transformOne
        );
    }

    private Contact transformOne(ResultSet resultSet) {
        try {
            return new SimpleContact(resultSet.getString("name"));
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

}

package guybrush.db;

import java.sql.*;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import javax.sql.DataSource;

/**
 *
 * @author Martín Straus <martinstraus@gmail.com>
 */
public class Queries {

    public static <T> Optional<T> selectOne(DataSource ds, String query, Function<ResultSet, T> transform, Object... parameters) {
        try (var connection = ds.getConnection()) {
            try (var select = connection.prepareStatement(query)) {
                setParameters(select, parameters);
                try (var resultSet = select.executeQuery()) {
                    return resultSet.next()
                            ? Optional.of(transform.apply(resultSet))
                            : Optional.empty();
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static <T> Set<T> selectSet(DataSource ds, String query, Function<ResultSet, T> transform, Object... parameters) {
        try (var connection = ds.getConnection()) {
            try (var select = connection.prepareStatement(query)) {
                setParameters(select, parameters);
                try (var resultSet = select.executeQuery()) {
                    Set<T> result = new HashSet<>();
                    while (resultSet.next()) {
                        result.add(transform.apply(resultSet));
                    }
                    return result;
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    private static void setParameters(PreparedStatement statement, Object... parameters) throws SQLException {
        if (parameters != null) {
            for (int i = 0; i < parameters.length; i++) {
                statement.setObject(i + 1, parameters[i]);
            }
        }
    }
}

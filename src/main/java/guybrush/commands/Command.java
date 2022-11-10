
package guybrush.commands;

import guybrush.telegram.User;
import java.util.Optional;

/**
 *
 * @author Martín Straus
 */
public interface Command {
    void execute(CommandContext context, Optional<User> from);
}

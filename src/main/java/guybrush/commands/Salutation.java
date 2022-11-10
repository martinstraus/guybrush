package guybrush.commands;

import guybrush.telegram.User;
import java.util.Optional;

/**
 *
 * @author Martín Straus
 */
public class Salutation implements Command {

    @Override
    public void execute(CommandContext context, Optional<User> from) {
        context.bot().send(from.get(), "¡Hola!");
    }

}

package ua.groupvertex.commands;

import org.telegram.telegrambots.api.objects.Update;

import java.util.regex.Pattern;

/**
 * Created by user on 10.07.2016.
 */
public class HelpCommand implements Command {

    private Pattern handlerPattern = Pattern.compile("/help");


    public Pattern getHandlerPattern() {
        return handlerPattern;
    }


    public String apply(Update update) {
        return new StringBuilder()
                .append("'/connect' to start game")
                .append("\n")
                .append("'x,y' to make move")
//                .append(update.getMessage().getFrom())
                .toString();
    }
}
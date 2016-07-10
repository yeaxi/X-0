package ua.groupvertex.commands;

import org.telegram.telegrambots.api.objects.Update;

import java.util.regex.Pattern;

/**
 * Created by user on 10.07.2016.
 */
public class HelloWorldCommand implements Command {

    private Pattern handlerPattern = Pattern.compile("/hello");


    public Pattern getHandlerPattern() {
        return handlerPattern;
    }


    public String apply(Update update) {
        return new StringBuilder()
                .append("Hello hello")
                .append(update.getMessage().getFrom())
                .toString();
    }
}
package ua.groupvertex.commands;

import org.telegram.telegrambots.api.objects.Update;

import java.util.regex.Pattern;

/**
 * Created by user on 10.07.2016.
 */
public interface Command  {

    Pattern getHandlerPattern();

    String apply(Update update);

    default boolean isHandles(String command){
        return getHandlerPattern().matcher(command).matches();
    }

}

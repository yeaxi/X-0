package ua.groupvertex.playground;

import org.telegram.telegrambots.TelegramApiException;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import ua.groupvertex.commands.Command;
import ua.groupvertex.commands.HelpCommand;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 10.07.2016.
 */
public class TelegramBot extends TelegramLongPollingBot {




    public String getBotUsername() {
        return "GameVertexXObot";
    }
    private String firstPlayer;
    private String secondPlayer;
    private boolean gameStarted;
    private int turn=1;
    private Playground playground = new SimplePlayGround();




    @Override
    public String getBotToken() {
        //return "260712345:AAGmBd9P15l65b6V_gz-vqimwZiC7ia7pXw";
//        return "265635125:AAESla6JTCp9RifuBEW335SFWseetCuURKY";
         return  "261753734:AAEOK37uN_isntxqQrPzl8UrwKnyuLqLDxg";
    }

    List<Command> commands=new ArrayList<>();
    {
        commands.add(new HelpCommand());
    }




    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
            if (gameStarted) {
                if ((turn==1)&&(firstPlayer.equals(message.getFrom().toString()))||
                        (turn==2)&&(secondPlayer.equals(message.getFrom().toString()))) {
                int a = Integer.parseInt(message.getText().substring(0, 1)) - 1;
                int b = Integer.parseInt(message.getText().substring(2)) - 1;
                //sendMsg(message,a+","+b);
                playground.doStep(a, b);
                sendMsg(message, playground.print());
                if (playground.doStep(a, b) == State.CROSS_WON) {
                    sendMsg(message, "'X' won! Bye!");
                    firstPlayer = null;
                    secondPlayer = null;
                    turn=1;
                    gameStarted=false;
                } else if (playground.doStep(a, b) == State.NOUGHT_WON) {
                    sendMsg(message, "'0' won! Bye!");
                    firstPlayer = null;
                    secondPlayer = null;
                    turn=1;
                    gameStarted=false;
                } else if (playground.doStep(a, b) == State.DRAW) {
                    sendMsg(message, "It's Draw! Bye!");
                    firstPlayer = null;
                    secondPlayer = null;
                    turn=1;
                    gameStarted=false;
                }
                    if (turn==1) {
                        turn=2;
                    } else {
                        turn=1;
                    }
            }
        }

        if (message != null && message.hasText()) {
            if (message.getText().equals("/connect")) {
                if (firstPlayer == null) {
                    firstPlayer = message.getFrom().toString();
                    sendMsg(message, "Player 1 connected");

                } else {
                    if (secondPlayer == null) {
                        secondPlayer = message.getFrom().toString();
                        sendMsg(message, "Player 2 connected");
                        sendMsg(message, "Game starting");
                        gameStarted=true;
                        playground.start();
                        sendMsg(message,playground.print());

                    }
                }
            }
        }

        for (Command c : commands) {
            if (c.isHandles(update.getMessage().getText())) {
                sendMsg(update.getMessage(), c.apply(update));
            }
        }


//        ua.groupvertex.commands.stream()
//                .filter(c -> c.isHandles(update.getMessage().getText()))
//                .forEach(c -> sendMsg(update.getMessage(),c.apply(update)));


//        Message message = update.getMessage();
//        if (message != null && message.hasText()) {
//            if (message.getText().equals("/help")) {
//                sendMsg(message, "Привет, я робот");
//            } else if(message.getText().equals("Давай сыграем в крестики-нолики")){
//                sendMsg(message, "я пока не умею");
//            } else
//                sendMsg(message, "Кто умеет играть ?");
//        }
        }


    public static void main(String[] args) {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new TelegramBot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void sendMsg(Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        //sendMessage.setReplayToMessageId(message.getMessageId());
        sendMessage.setText(text);
        try {
            sendMessage(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

}

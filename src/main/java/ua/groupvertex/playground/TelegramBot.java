package ua.groupvertex.playground;

import org.telegram.telegrambots.TelegramApiException;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import ua.groupvertex.commands.Command;

import java.util.List;

/**
 * Created by user on 10.07.2016.
 */
public class TelegramBot extends TelegramLongPollingBot {




    public String getBotUsername() {
        return "GameVertexXObot";
    }

    @Override
    public String getBotToken() {
        //return "260712345:AAGmBd9P15l65b6V_gz-vqimwZiC7ia7pXw";
//        return "265635125:AAESla6JTCp9RifuBEW335SFWseetCuURKY";
         return  "229953195:AAEyG344uiuseKpAbUtm97zaY3AgsJtHXgg";
    }

    List<Command> commands;

    public void onUpdateReceived(Update update) {
        for (Command c : commands){
            if (c.isHandles(update.getMessage().getText())){
                sendMsg(update.getMessage(),c.apply(update));
            }
        }
//        ua.groupvertex.commands.stream()
//                .filter(c -> c.isHandles(update.getMessage().getText()))
//                .forEach(c -> sendMsg(update.getMessage(),c.apply(update)));



        Message message = update.getMessage();
        if (message != null && message.hasText()) {
            if (message.getText().equals("/help")) {
                sendMsg(message, "Привет, я робот");
            } else if(message.getText().equals("Давай сыграем в крестики-нолики")){
                sendMsg(message, "я пока не умею");
            }else
                sendMsg(message, "Кто умеет играть ?");
        }
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

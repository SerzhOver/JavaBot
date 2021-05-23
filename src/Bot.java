import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Bot extends TelegramLongPollingBot {
    private ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
    private String Msg = "";
    private HashMap<String, Integer> stations = Station.getStations();
    private long chat_id;

    public void onUpdateReceived(Update update) {
        Station.getStations();
        chat_id = update.getMessage().getChatId();

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chat_id);
        sendMessage.setText(getMessage(update.getMessage().getText()));

        try {
            execute(sendMessage);


        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }


    private String getMessage(String firstMsg) {
        ArrayList<KeyboardRow> keyboard = new ArrayList<KeyboardRow>();

        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);

        if (firstMsg.equalsIgnoreCase("привіт") || firstMsg.equalsIgnoreCase("меню")
                || firstMsg.equals("/start")) {
            keyboard.clear();
            return "Привіт, напиши  назви станції відправлення";
        }

        if (stations.containsKey(firstMsg.toLowerCase())) {
            if (stations.containsKey(Msg)) {
                try {
                    return getTrain(new Menu().getTrains(Msg, firstMsg));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        if (stations.containsKey(firstMsg.toLowerCase())) {
            Msg = firstMsg.toLowerCase();
            return "Введи станцію прибуття";
        }
        return "Упс, такої станції не існує";
    }

    public String getTrain(String[] text) {
        SendMessage sendMessage = new SendMessage().setChatId(chat_id);
        Msg = "";

        for (int i = 0; i < text.length; i++) {
            try {
                if ((i + 1) == text.length) {
                    return text[i];
                }

                sendMessage.setText(text[i]);
                execute(sendMessage);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public String getBotUsername() {
        return "@ScheduleOfTrainsBot";
    }

    @Override
    public String getBotToken() {
        return "1667277359:AAGYV7Lg1tblkgUrdqjwe__ijjr-sISCDlM";
    }

}


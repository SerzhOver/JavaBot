import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.util.ArrayList;

public class Bot extends TelegramLongPollingBot {
    ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
    long chat_id;

    public void onUpdateReceived(Update update) {
        chat_id = update.getMessage().getChatId();

        SendMessage sendMessage = new SendMessage().setChatId(chat_id)
                .setText(getMessage(update.getMessage().getText()));
        sendMessage.setReplyMarkup(replyKeyboardMarkup);

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public String getForCase(String msg) {
        try {
            return getTrain(new Menu().getTrains(msg));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return msg;
    }

    public String getMessage(String msg) {
        ArrayList<KeyboardRow> keyboard = new ArrayList<KeyboardRow>();

        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);


        if (msg.equalsIgnoreCase("Привіт") || msg.equalsIgnoreCase("Меню")
                || msg.equals("/start")) {
            keyboard.clear();
            return "Привіт, напиши  назви зупинки";
        }
        switch (msg.toLowerCase()) {
            case "аккаржа":
                getForCase(msg);
            case "антонівка":
                getForCase(msg);
                break;
            case "апостолове":
                getForCase(msg);
            case "алтинівка":
                getForCase(msg);
            case "бугаз":
                getForCase(msg);
            case "букачівці":
                getForCase(msg);
            case "бурштин":
                getForCase(msg);
            case "березине":
                getForCase(msg);
        }

        return null;
    }

    public String getTrain(String[] text) {
        SendMessage sendMessage = new SendMessage().setChatId(chat_id);
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


import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import myPack.Currency;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import java.lang.reflect.Type;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;


public class Main extends TelegramLongPollingBot {
    public static void main(String[] args) {


        ApiContextInitializer.init();
        TelegramBotsApi api = new TelegramBotsApi();

        try {
            api.registerBot(new Main());

        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }

    }

    @Override
    public String getBotToken() {
        return "5165465236:AAEs8ncW23SGzFA_Jf-xOzkE_my5g8UJC_4";
    }

    @Override
    public void onUpdateReceived(Update update) {

        String text = update.getMessage().getText();
        SendMessage sendMessage = new SendMessage();

        if (text.equals("/start")) {
            sendMessage.setText("bot ga xush kelibsiz.");
            sendMessage.setChatId(update.getMessage().getChatId());
        }

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try {
            URL url = new URL("https://cbu.uz/oz/arkhiv-kursov-valyut/json/");
            URLConnection urlConnection = url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            Type type = new TypeToken<ArrayList<Currency>>() {}.getType();
            ArrayList<Currency> currencies = gson.fromJson(reader, type);


            // SELECT METHOD
            if(ExtraMethods.parseDouble(text)&&MainMethod.i!=0){
                double sum=Double.parseDouble(text);
                switch (MainMethod.i){
                    case 1:
                        sendMessage.setChatId(update.getMessage().getChatId());
                        sendMessage.setText(MainMethod.dollarToSom(currencies, sum));
                        break;
                    case 2:
                        sendMessage.setChatId(update.getMessage().getChatId());
                        sendMessage.setText(MainMethod.somToDollar(currencies, sum));
                        break;
                    case 3:
                        sendMessage.setChatId(update.getMessage().getChatId());
                        sendMessage.setText(MainMethod.euroToSom(currencies, sum));
                        break;
                    case 4:
                        sendMessage.setChatId(update.getMessage().getChatId());
                        sendMessage.setText(MainMethod.somToEuro(currencies, sum));
                        break;
                    case 5:
                        sendMessage.setChatId(update.getMessage().getChatId());
                        sendMessage.setText(MainMethod.yuanToSom(currencies, sum));
                        break;
                    case 6:
                        sendMessage.setChatId(update.getMessage().getChatId());
                        sendMessage.setText(MainMethod.somToYuan(currencies, sum));
                        break;
                }
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        //CREATING ROWS
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);
        replyKeyboardMarkup.setSelective(true);

        List<KeyboardRow> keyboardRows = new ArrayList<>();

        KeyboardRow row1 = new KeyboardRow();

        KeyboardButton button1 = new KeyboardButton("dollar=>so'm");
        KeyboardButton button2 = new KeyboardButton("so'm=>dollar");
        row1.add(button1);
        row1.add(button2);

        KeyboardRow row2 = new KeyboardRow();

        KeyboardButton button3 = new KeyboardButton("euro=>so'm");
        KeyboardButton button4 = new KeyboardButton("so'm=>euro");
        row2.add(button3);
        row2.add(button4);
        
        
        KeyboardRow row3 = new KeyboardRow();
        row3.add("yuan=>so'm");
        row3.add("so'm=>yuan");

        keyboardRows.add(row1);
        keyboardRows.add(row2);
        keyboardRows.add(row3);

        replyKeyboardMarkup.setKeyboard(keyboardRows);





            switch (text){
                case "dollar=>so'm":
                    MainMethod.i=1;
                    sendMessage.setText("Enter the money(dollar=>so'm)");
                    sendMessage.setChatId(update.getMessage().getChatId());
                    break;
                case "so'm=>dollar":
                    MainMethod.i=2;
                    sendMessage.setText("Enter the money(so'm=>dollar)");
                    sendMessage.setChatId(update.getMessage().getChatId());
                    break;
                case "euro=>so'm":
                    MainMethod.i=3;
                    sendMessage.setText("Enter the money(euro=>so'm)");
                    sendMessage.setChatId(update.getMessage().getChatId());
                    break;
                case "so'm=>euro":
                    MainMethod.i=4;
                    sendMessage.setText("Enter the money(so'm=>euro)");
                    sendMessage.setChatId(update.getMessage().getChatId());
                    break;
                case "yuan=>so'm":
                    MainMethod.i=5;
                    sendMessage.setText("Enter the money(yuan=>som)");
                    sendMessage.setChatId(update.getMessage().getChatId());
                    break;
                case "so'm=>yuan":
                    MainMethod.i=6;
                    sendMessage.setText("Enter the money(so'm=>yuan)");
                    sendMessage.setChatId(update.getMessage().getChatId());
                    break;
            }

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }


    }

    @Override
    public String getBotUsername() {
        return "@topshiriq1Bot";
    }
}
/*
1-vazifa

Markaziy bank api ga bog'langan holda 3 valyuta (dollar, euro va yuan)
bo'yicha valyutani so'mga yoki so'mni valyutaga ayriboshlash imkonini
beruvchi telegram bot yarating

*/
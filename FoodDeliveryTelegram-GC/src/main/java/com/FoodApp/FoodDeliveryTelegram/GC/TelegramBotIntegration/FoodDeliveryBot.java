package com.FoodApp.FoodDeliveryTelegram.GC.TelegramBotIntegration;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public class FoodDeliveryBot extends TelegramLongPollingBot {

    @Override
    public String getBotUsername() {
      return "EasyFoodDeliveryBotwithGC";
    }

    @Override
    public String getBotToken() {
        return "8195066710:AAEUfodoq54KbjgRF3tHRMHy9ejw8uOJ4iIs";
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String message = update.getMessage().getText();
            SendMessage response = new SendMessage();
            response.setChatId(update.getMessage().getChatId().toString());
            
            if (message.equalsIgnoreCase("/menu")) {
                response.setText("Menu:\n1. Pizza - $10\n2. Burger - $5\n3. Pasta - $8");
            } else {
                response.setText("Send /menu to see available items!");
            }
            try {
                execute(response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}







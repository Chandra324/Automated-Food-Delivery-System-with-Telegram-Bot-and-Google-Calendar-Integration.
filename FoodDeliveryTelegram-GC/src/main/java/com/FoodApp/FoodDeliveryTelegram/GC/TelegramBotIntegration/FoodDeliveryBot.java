package com.FoodApp.FoodDeliveryTelegram.GC.TelegramBotIntegration;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;



   import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class FoodDeliveryBot extends TelegramLongPollingBot {

    // Menu items
    private static final Map<String, Integer> MENU = new LinkedHashMap<>() {{
        put("Pizza", 10);
        put("Burger", 8);
        put("Pasta", 12);
        put("Salad", 6);
    }};

    // Store user orders
    private final Map<Long, HashMap<String, Integer>> userOrders = new HashMap<>();

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();

            if (messageText.equalsIgnoreCase("/start")) {
                sendMessage(chatId, "Welcome to FoodOrderBot! Use /menu to see the menu or /order to place an order.");
            } else if (messageText.equalsIgnoreCase("/menu")) {
                sendMenu(chatId);
            } else if (messageText.equalsIgnoreCase("/order")) {
                sendMessage(chatId, "Please type the item name and quantity in this format: ItemName:Quantity\nFor example: Pizza:2");
            } else if (messageText.equalsIgnoreCase("/bill")) {
                generateBill(chatId);
            } else if (messageText.contains(":")) {
                processOrder(chatId, messageText);
            } else {
                sendMessage(chatId, "Invalid command. Use /menu, /order, or /bill.");
            }
        }
    }

    private void sendMenu(long chatId) {
        StringBuilder menu = new StringBuilder("Here is our menu:\n");
        for (Map.Entry<String, Integer> item : MENU.entrySet()) {
            menu.append(item.getKey()).append(" - $").append(item.getValue()).append("\n");
        }
        sendMessage(chatId, menu.toString());
    }

    private void processOrder(long chatId, String messageText) {
        String[] parts = messageText.split(":");
        if (parts.length == 2) {
            String itemName = parts[0].trim();
            int quantity;
            try {
                quantity = Integer.parseInt(parts[1].trim());
            } catch (NumberFormatException e) {
                sendMessage(chatId, "Invalid quantity. Please try again.");
                return;
            }

            if (MENU.containsKey(itemName)) {
                userOrders.putIfAbsent(chatId, new HashMap<>());
                HashMap<String, Integer> orders = userOrders.get(chatId);
                orders.put(itemName, orders.getOrDefault(itemName, 0) + quantity);
                sendMessage(chatId, "Added " + quantity + " " + itemName + "(s) to your order.");
            } else {
                sendMessage(chatId, "Item not found in the menu. Please try again.");
            }
        } else {
            sendMessage(chatId, "Invalid format. Please use ItemName:Quantity.");
        }
    }

    private void generateBill(long chatId) {
        if (!userOrders.containsKey(chatId) || userOrders.get(chatId).isEmpty()) {
            sendMessage(chatId, "You have no items in your order.");
            return;
        }

        StringBuilder bill = new StringBuilder("Your Order:\n");
        HashMap<String, Integer> orders = userOrders.get(chatId);
        int total = 0;

        for (Map.Entry<String, Integer> entry : orders.entrySet()) {
            String itemName = entry.getKey();
            int quantity = entry.getValue();
            int price = MENU.get(itemName) * quantity;
            total += price;
            bill.append(itemName).append(" x ").append(quantity).append(" = $").append(price).append("\n");
        }

        bill.append("Total: $").append(total);
        sendMessage(chatId, bill.toString());
    }

    private void sendMessage(long chatId, String text) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(text);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return "EasyFoodDeliveryBotwithGC"; // Replace with your bot's username
    }

    @Override
    public String getBotToken() {
        return "8195066710:AAEUfodoq54KbjgRF3tHRMHy9ejw8uOJ4iIs"; // Replace with your bot's token
    }
}





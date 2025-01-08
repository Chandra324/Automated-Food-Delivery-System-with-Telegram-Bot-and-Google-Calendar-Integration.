package com.task.todolist.bot;

import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class BotListener implements ServletContextListener {
    private BotThread botThread;  //bot's operations.
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {//Called when the ServletContext is initialized, typically when the web application starts.
        try {
            botThread = new BotThread(servletContextEvent.getServletContext());
        } catch (TelegramApiException e) {  //errors during the initialization
            throw new RuntimeException(e);
        }
        botThread.start();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        botThread.stopBot();
    }
}
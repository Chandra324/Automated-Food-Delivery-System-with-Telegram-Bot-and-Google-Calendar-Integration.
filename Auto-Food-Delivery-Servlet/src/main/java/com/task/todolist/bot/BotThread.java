package com.task.todolist.bot;

import com.task.todolist.model.MyTelegramBot;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import javax.servlet.ServletContext;

public class BotThread extends Thread {
    private MyTelegramBot bot;
  public BotThread(ServletContext servletContext) throws TelegramApiException {  //Constructor that 
    	//initializes the bot with the application's ServletContext.

        System.out.println("initbot");//rints a message to the console, 
        //indicating that the bot initialization is starting.

        bot = new MyTelegramBot(servletContext);//passing the ServletContext 
        //for access to configuration or shared resources.
    }

    @Override  //the run() method of the Thread class.
    public void run() {
        try {
            bot.runBot();//method to start its core functionalities
            System.out.println("BotThread: Started");//Logs a message 
            //indicating that the bot thread has started successfully.
       while (bot.isRunning()) {//continuously checks whether the bot is still running b
                Thread.sleep(1000);
            }
            System.out.println("BotThread: Stopped");}
 catch (TelegramApiException | InterruptedException e) {//Handles exceptions related to bot 
	                                                    //operation failures
            throw new RuntimeException(e);
        }
    }

    public void stopBot() {//stop out side class and cleanly terminate
        bot.stopBot();
    }
}
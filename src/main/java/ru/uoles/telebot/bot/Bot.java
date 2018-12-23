package ru.uoles.telebot.bot;

import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.uoles.telebot.bot.commands.BaseCommand;

import java.util.List;
import java.util.Properties;

/**
 * Created by Maksim Kulikov on 2018-02-19.
 */
public class Bot extends TelegramLongPollingBot {

    private static Logger log = Logger.getLogger(Bot.class.getName());
    private Properties property;

    public Bot(DefaultBotOptions options, Properties property) {
        super(options);
        this.property = property;
    }

    @Override
    public void onUpdateReceived(Update update) {
        processMessage(update);
    }

    @Override
    public void onUpdatesReceived(List<Update> updates) {
        for (Update update : updates) {
            processMessage(update);
        }
    }

    private void processMessage(Update update) {
        // We check if the update has a message and the message has text
        if (update.hasMessage() && update.getMessage().hasText()) {
            // Set variables
            String commandName = update.getMessage().getText();
            Long chatId = update.getMessage().getChatId();

            String responceMessage = ".";
            ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
            try {
                BaseCommand baseCommand = (BaseCommand) context.getBean(commandName);
                responceMessage = baseCommand.execute();
            } finally {
                context.close();
            }

            if (".".equals(responceMessage) || "".equals(responceMessage)) {
                responceMessage = "Нет ответа..";
            }

            // Create a message object object
            SendMessage message = new SendMessage()
                    .setChatId(chatId)
                    .setText(responceMessage);
            try {
                // Sending our message object to user
                execute(message);
            } catch (TelegramApiException e) {
                log.error("Sending message error: " + e.getMessage());
            }

            System.out.println("-- CommandName: \n" + commandName);
            System.out.println("-- ResponceMessage: \n" + responceMessage);
            System.out.println("---------------------");
        }
    }

    public String getBotUsername() {
        return property.getProperty("bot.name");
    }

    public String getBotToken() {
        return property.getProperty("bot.key");
    }
}

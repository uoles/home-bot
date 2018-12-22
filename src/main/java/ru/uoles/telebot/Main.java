package ru.uoles.telebot;

import org.apache.log4j.Logger;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.uoles.telebot.bot.Bot;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by Maxim Kulikov on 2018-02-19.
 */
public class Main {

    private static Logger log = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        log.info("Start bot");

        FileInputStream fis;
        Properties property = new Properties();

        try {
            fis = new FileInputStream("src/main/resources/bot.properties");
            property.load(fis);

            // Initialize Api Context
            ApiContextInitializer.init();
            // Instantiate Telegram Bots API
            TelegramBotsApi botsApi = new TelegramBotsApi();
            // Register our bot
            try {
                DefaultBotOptions options = new DefaultBotOptions();
                options.setProxyType(DefaultBotOptions.ProxyType.HTTP);
                options.setProxyHost( property.getProperty("proxy.host") );
                options.setProxyPort( Integer.parseInt(property.getProperty("proxy.port")) );

                botsApi.registerBot(new Bot(options, property));
            } catch (TelegramApiException e) {
                log.error("Register bot error: " + e.getMessage());
            }
        } catch (FileNotFoundException e) {
            log.info("Main error: " + e.getMessage());
        } catch (IOException e) {
            log.info("Main error: " + e.getMessage());
        } finally {
            log.info("Stop bot.");
        }
    }
}

package ru.uoles.telebot;

import org.apache.log4j.Logger;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.uoles.telebot.bot.Bot;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Maksim Kulikov on 2018-02-19.
 */
public class Main {

    private static Logger log = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        toLog("Bot loading start.");

        InputStream input = null;
        Properties property = new Properties();

        try {
            String filename = "bot.properties";
            input = Main.class.getClassLoader().getResourceAsStream(filename);
            if (input == null) {
                toLog("Sorry, unable to find " + filename);
                return;
            }
            property.load(input);
            toLog("Load properties");

            // Initialize Api Context
            ApiContextInitializer.init();
            toLog("ApiContextInitializer init");

            // Instantiate Telegram Bots API
            TelegramBotsApi botsApi = new TelegramBotsApi();
            // Register our bot
            try {
                DefaultBotOptions options = new DefaultBotOptions();
                options.setProxyType(DefaultBotOptions.ProxyType.HTTP);
                options.setProxyHost(property.getProperty("proxy.host"));
                options.setProxyPort(Integer.parseInt(property.getProperty("proxy.port")));
                toLog("Set proxy settings");

                botsApi.registerBot(new Bot(options, property));
                toLog("Register bot");
            } catch (TelegramApiException e) {
                toLog("Register bot error: " + e.getMessage());
            }
        } catch (IOException e) {
            toLog("Error: " + e.getMessage());
        } finally {
            toLog("Bot loading end.");
            toLog("---------------------");
        }
    }

    private static void toLog(String text) {
        log.info(text);
        System.out.println(text);
    }
}

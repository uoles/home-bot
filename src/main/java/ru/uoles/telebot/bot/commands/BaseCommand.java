package ru.uoles.telebot.bot.commands;

import org.apache.log4j.Logger;

import java.io.IOException;

/**
 * Created by Maxim Kulikov on 2018-02-20.
 */
public abstract class BaseCommand {

    public Logger log = Logger.getLogger(getClass().getName());

    /**
     * Обработка команды. Возвращает ответное сообщение.
     *
     * @return
     */
    public String execute() {
        log.info(getLogS());

        String result;
        try {
            process();
            log.info(getLogS() + ": Ok");
            result = "Ok";
        } catch (IOException e) {
            result = getLogS() + " error: " + e.getMessage();
        }
        log.error(result);

        return result;
    }

    public abstract void process() throws IOException;

    public String getLogS() {
        return getClass().getName() + ".process";
    }
}

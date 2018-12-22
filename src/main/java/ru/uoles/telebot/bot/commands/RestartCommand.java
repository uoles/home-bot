package ru.uoles.telebot.bot.commands;

import java.io.IOException;

/**
 * Created by Maxim Kulikov on 2018-02-19.
 */
public class RestartCommand extends BaseCommand {

    @Override
    public void process() throws IOException {
        Process shutdown = Runtime.getRuntime().exec(new String[]{"shutdown", "-r"});
    }
}

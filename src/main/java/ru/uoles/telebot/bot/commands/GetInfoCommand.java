package ru.uoles.telebot.bot.commands;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class GetInfoCommand extends BaseCommand {

    @Override
    public String execute() {
        log.info(getLogS());

        String result;
        try {
            URL url = new URL("http://checkip.amazonaws.com/");
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
            result = "Internet ip: " + br.readLine();
        } catch (Exception e) {
            result = getLogS() + " error: " + e.getMessage();
        }
        return result;
    }

    @Override
    public void process() throws IOException {
        //...
    }
}

package ru.uoles.telebot.bot.commands;

/**
 * Created by Maxim Kulikov on 2018-02-20.
 */
public class HelpCommand extends BaseCommand {

    @Override
    public String execute() {
        log.info(getLogS());

        return new String(
                "****************************************\n" +
                        "Список доступных команд:\n" +
                        "Restart - перезагрузка машины\n" +
                        "Shutdown - выключение машины\n" +
                        "WakeUp - разбудить компьютер\n" +
                        "Help - помощь\n" +
                        "****************************************\n"
        );
    }

    @Override
    public void process() {
        //..
    }
}

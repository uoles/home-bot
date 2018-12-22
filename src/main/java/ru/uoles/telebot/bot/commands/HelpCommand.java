package ru.uoles.telebot.bot.commands;

/**
 * Created by Maksim Kulikov on 2018-02-20.
 */
public class HelpCommand extends BaseCommand {

    @Override
    public String execute() {
        log.info(getLogS());

        return new String(
                "****************************************\n" +
                        "Список доступных команд:\n" +
                        "restart - перезагрузка компьютера\n" +
                        "shutdown - выключение компьютера\n" +
                        "wakeUp - разбудить компьютер\n" +
                        "help - помощь\n" +
                        "getInfo - текущий IP адрес\n" +
                        "****************************************\n"
        );
    }

    @Override
    public void process() {
        //..
    }
}

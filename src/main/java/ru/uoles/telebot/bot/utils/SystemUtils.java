package ru.uoles.telebot.bot.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SystemUtils {

    private static final String TASKLIST = "tasklist";
    private static final String KILL = "taskkill /F /IM ";

    /**
     * Убиваем процесс.
     *
     * @param serviceName
     * @return
     * @throws Exception
     */
    public static boolean killProcess(String serviceName) throws Exception {
        Boolean result = false;
        if (isProcessRunning(serviceName)) {
            Runtime.getRuntime().exec(KILL + serviceName);
            result = true;
        }
        return result;
    }

    /**
     * Проверяем наличие процесса.
     *
     * @param serviceName
     * @return
     * @throws Exception
     */
    public static boolean isProcessRunning(String serviceName) throws Exception {
        Process p = Runtime.getRuntime().exec(TASKLIST);
        BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));

        String line;
        while ((line = reader.readLine()) != null) {
            if (line.contains(serviceName)) {
                return true;
            }
        }

        return false;
    }

}

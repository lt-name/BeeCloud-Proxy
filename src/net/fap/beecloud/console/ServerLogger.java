package net.fap.beecloud.console;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ServerLogger {

    public static void info(String message)
    {
        System.out.println(getTime()+"[INFO] "+message);
    }

    public static void waring(String message)
    {
        System.out.println(getTime()+"[WARING] "+message);
    }

    private static String getTime()
    {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return "["+sdf.format(date)+"]";
    }

}

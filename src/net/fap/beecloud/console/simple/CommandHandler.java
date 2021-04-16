package net.fap.beecloud.console.simple;

import net.fap.beecloud.console.ServerLogger;

import java.util.HashMap;

public class CommandHandler {

    public static HashMap<String,CommandHandler> commandMap = new HashMap<>();

    public String commandStr;

    public static void registerCommand(CommandHandler commandHandler)
    {
        commandMap.put(commandHandler.commandStr,commandHandler);
    }

    public static void handleCommand(String commandStr)
    {
        if (commandMap.containsKey(commandStr))
        {
            commandMap.get(commandStr).runCommand();
        }else ServerLogger.info("Unknown command! type help for help.");
    }

    public void setCommandStr(String commandStr)
    {
        this.commandStr = commandStr;
    }

    public void runCommand()
    {

    }

}

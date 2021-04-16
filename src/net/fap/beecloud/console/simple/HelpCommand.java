package net.fap.beecloud.console.simple;

import net.fap.beecloud.console.ServerLogger;

public class HelpCommand extends CommandHandler {

    public String commandStr = "help";

    public HelpCommand(){
        setCommandStr(this.commandStr);
    }

    @Override
    public void setCommandStr(String commandStr) {
        super.setCommandStr(this.commandStr);
    }

    @Override
    public void runCommand() {
        ServerLogger.info("-Command List-");
        for (String str:CommandHandler.commandMap.keySet())
            ServerLogger.info(str+" - Default command");
        super.runCommand();
    }
}

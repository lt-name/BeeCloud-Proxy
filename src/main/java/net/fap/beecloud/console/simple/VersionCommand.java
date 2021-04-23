package net.fap.beecloud.console.simple;

import net.fap.beecloud.console.ServerLogger;

public class VersionCommand extends CommandHandler {

    public String commandStr = "version";
    public String commandUsage = "查看BeeCloud的版本";

    public VersionCommand()
    {
        this.setCommandStr(this.commandStr,this.commandUsage);
    }

    @Override
    public void setCommandStr(String commandStr, String commandUsage) {
        super.setCommandStr(commandStr, commandUsage);
    }

    @Override
    public void runCommand() {
        ServerLogger.info("此服务器正运行在BeeCloud-Proxy上. Build: BetaVersion 0.0.1");
    }
}

package net.fap.beecloud.console.simple;

public class StopCommand extends CommandHandler{

    public String commandStr = "stop";
    public String commandUsage = "停止服务器";

    public StopCommand(){
        setCommandStr(this.commandStr,this.commandUsage);
    }

    @Override
    public void setCommandStr(String commandStr, String commandUsage) {
        super.setCommandStr(commandStr, commandUsage);
    }

    @Override
    public void runCommand() {
        System.exit(0);
        super.runCommand();
    }

}

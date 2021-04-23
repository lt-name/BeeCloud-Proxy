package net.fap.beecloud.console.simple;

import net.fap.beecloud.Server;
import net.fap.beecloud.SynapsePlayer;
import net.fap.beecloud.console.ServerLogger;

public class ListCommand extends CommandHandler{

    public String commandStr = "list";
    public String commandUsage = "查看在线玩家列表";

    public ListCommand(){
        setCommandStr(this.commandStr,this.commandUsage);
    }

    @Override
    public void setCommandStr(String commandStr, String commandUsage) {
        super.setCommandStr(commandStr, commandUsage);
    }

    @Override
    public void runCommand() {
        ServerLogger.info("Player Online("+Server.onLinePlayerList.size()+")");
        for (SynapsePlayer player: Server.onLinePlayerList)
            System.out.println(player.player+" ");
        super.runCommand();
    }

}

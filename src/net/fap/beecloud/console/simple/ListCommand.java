package net.fap.beecloud.console.simple;

import net.fap.beecloud.Server;
import net.fap.beecloud.SynapsePlayer;
import net.fap.beecloud.console.ServerLogger;

public class ListCommand extends CommandHandler{

    public String commandStr = "list";

    public ListCommand(){
        setCommandStr(this.commandStr);
    }

    @Override
    public void setCommandStr(String commandStr) {
        super.setCommandStr(this.commandStr);
    }

    @Override
    public void runCommand() {
        ServerLogger.info("Player Online("+Server.onLinePlayerList.size()+")");
        for (SynapsePlayer player: Server.onLinePlayerList)
            System.out.println(player.player+" ");
        super.runCommand();
    }

}

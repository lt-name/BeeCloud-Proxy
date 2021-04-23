package net.fap.beecloud;

import cn.nukkit.network.protocol.DisconnectPacket;
import net.fap.beecloud.console.ServerLogger;
import net.fap.beecloud.network.mcpe.protocol.LoginPacket;
import net.fap.beecloud.network.mcpe.protocol.QuitPacket;

import java.util.UUID;

/**
 * FillAmeaPixel Project
 * BeeCloud Server
 * @author catrainbow
 */

public class SynapsePlayer {

    public String clientUUid;
    public String clientID;
    public String player;
    public String address;;

    public SynapsePlayer(String player, String address, String clientUUId, String clientID)
    {
        this.clientUUid = clientUUId;
        this.clientID = clientID;
        this.player = player;
        this.address = address;
    }

    public static void addPlayer(LoginPacket packet)
    {
        Server.onLinePlayerList.add(new SynapsePlayer(packet.getPlayer(),packet.address,packet.uuid,packet.clientID));
        ServerLogger.info(packet.getPlayer()+"("+packet.address+") joined the game.");
    }

    public static void removePlayer(QuitPacket packet)
    {
        Server.onLinePlayerList.remove(getPlayer(packet.getPlayer()));
        ServerLogger.info(packet.getPlayer()+" quited the game.");
    }

    public static SynapsePlayer getPlayer(String player)
    {
        for (int i=0; i<Server.onLinePlayerList.size(); i++)
            if (Server.onLinePlayerList.get(i).player.equals(player))
                return Server.onLinePlayerList.get(i);
            return null;
    }


}

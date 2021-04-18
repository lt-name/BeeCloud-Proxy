package net.fap.beecloud;

import cn.nukkit.network.protocol.DisconnectPacket;

import java.util.UUID;

/**
 * FillAmeaPixel Project
 * BeeCloud Server
 * @author catrainbow
 */

public class SynapsePlayer {

    public UUID clientUUid;
    public long clientID;
    public String player;
    public String address;;

    public SynapsePlayer(String player, String address, UUID clientUUId, long clientID)
    {
        this.clientUUid = clientUUId;
        this.clientID = clientID;
        this.player = player;
        this.address = address;
    }

    public static void addPlayer(String[] pk2)
    {
        String name = pk2[0];
        String address = pk2[1];
        String uuid = pk2[2];
        String clientID = pk2[3];
        Server.onLinePlayerList.add(new SynapsePlayer(name,address,UUID.randomUUID(),(long) Integer.valueOf(clientID)));
    }

    public static SynapsePlayer getPlayer(String player)
    {
        for (int i=0; i<Server.onLinePlayerList.size(); i++)
            if (Server.onLinePlayerList.get(i).player.equals(player))
                return Server.onLinePlayerList.get(i);
            return null;
    }

    public static void removePlayer(DisconnectPacket packet)
    {

    }


}

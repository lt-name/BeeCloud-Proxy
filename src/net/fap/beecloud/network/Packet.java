package net.fap.beecloud.network;

import cn.nukkit.Server;
import cn.nukkit.network.protocol.DataPacket;
import cn.nukkit.network.protocol.LoginPacket;
import cn.nukkit.network.protocol.MovePlayerPacket;
import net.fap.beecloud.BeeCloud;
import net.fap.beecloud.SynapsePlayer;
import net.fap.beecloud.console.ServerLogger;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Packet {

    public static void handlePacket(String pk)
    {
        DataPacket pk1 = typePacket(pk);
        if (pk1==null){
            if (pk.contains("LoginPacket"))
            {
                String[] pk2 = pk.split(":");
                String name = pk2[1];
                String address = pk2[2];
                String uuid = pk2[3];
                ServerLogger.info(name+"("+address+") joined the game. ("+uuid+")");
                SynapsePlayer.addPlayer(pk2);
                return;
            }
        }
        if (pk1 instanceof MovePlayerPacket)
        {
            MovePlayerPacket pk2 = new MovePlayerPacket();
            pk2.putString(pk);
            BeeCloud.server.send(pk2);
        }

    }

    public static DataPacket typePacket(String pk)
    {
        if (pk.contains("MovePlayerPacket"))
        {
            MovePlayerPacket pk1 = new MovePlayerPacket();
            pk1.putString(pk);
            return pk1;
        }
        return null;
    }


}

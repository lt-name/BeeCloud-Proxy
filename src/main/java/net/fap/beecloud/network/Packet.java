package net.fap.beecloud.network;

import cn.nukkit.network.protocol.DataPacket;
import cn.nukkit.network.protocol.MovePlayerPacket;
import net.fap.beecloud.BeeCloud;
import net.fap.beecloud.SynapsePlayer;
import net.fap.beecloud.console.ServerLogger;
import net.fap.beecloud.event.server.DataPacketReceiveEvent;
import net.fap.beecloud.network.mcpe.protocol.*;

public class Packet {

    public static void handlePacket(String pk)
    {
        DataPacket pk1 = typePacket(pk);
        if (pk1==null){
            if (pk.contains("LoginPacket"))
            {
                String[] pk2 = pk.split("\\:");
                LoginPacket pk3 = new LoginPacket();
                pk3.putString(pk2);
                BeeCloud.server.send(pk3);
                DataPacketReceiveEvent event = new DataPacketReceiveEvent(pk3);
                event.call();
                return;
            }
            else if (pk.contains("QuitPacket"))
            {
                String[] pk2 = pk.split("\\:");
                QuitPacket pk3 = new QuitPacket();
                pk3.putString(pk2);
                BeeCloud.server.send(pk3);
                DataPacketReceiveEvent event = new DataPacketReceiveEvent(pk3);
                event.call();
                return;
            }
            else if (pk.contains("ServerUpdatePacket"))
            {
                ServerUpdatePacket packet = new ServerUpdatePacket();
                BeeCloud.server.send(packet);
                DataPacketReceiveEvent event = new DataPacketReceiveEvent(packet);
                event.call();
            }else if (pk.contains("ServerChatPacket"))
            {
                String[] pk2 = pk.split("\\:");
                ServerChatPacket pk3 = new ServerChatPacket(pk2[2],pk2[1],pk2[3]);
                pk3.putString(pk2);
                BeeCloud.server.send(pk3);
                DataPacketReceiveEvent event = new DataPacketReceiveEvent(pk3);
                event.call();
            }else if (pk.contains("ConnectPacket"))
            {
                String[] pk2 = pk.split("\\:");
                ConnectPacket pk3 = new ConnectPacket(pk2[2],pk2[1],pk2[3],pk2[4],pk2[5]);
                BeeCloud.server.send(pk3);
                DataPacketReceiveEvent event = new DataPacketReceiveEvent(pk3);
                event.call();
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

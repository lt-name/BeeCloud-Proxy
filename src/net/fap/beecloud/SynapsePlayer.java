package net.fap.beecloud;

import cn.nukkit.Player;
import net.fap.beecloud.network.mcpe.DataPacket;
import net.fap.beecloud.network.mcpe.TextPacket;

import java.util.UUID;

/**
 * FillAmeaPixel Project
 * BeeCloud Server
 * @author catrainbow
 */

public class SynapsePlayer {

    public double x;
    public double y;
    public double z;
    public Player player;
    public UUID uuid;

    public SynapsePlayer(Player player)
    {
        this.player = player;
        this.uuid = player.getUniqueId();
    }

    public SynapsePlayer getPlayer()
    {
        return this;
    }

    public double getX()
    {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public double getZ() {
        return this.z;
    }

    public void sendPacketMessage(String string)
    {
        TextPacket pk1 = new TextPacket();
        pk1.setPlayer(this.player); pk1.putString(string);

    }

    public void sendPacketTitle(DataPacket packet)
    {
        if (packet instanceof TextPacket)
        {
            player.sendTitle(((TextPacket) packet).getText());
        }
    }

    public void sendPacket()
    {

    }

}

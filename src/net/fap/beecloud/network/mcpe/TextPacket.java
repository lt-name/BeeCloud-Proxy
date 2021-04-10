package net.fap.beecloud.network.mcpe;

import cn.nukkit.Player;

public class TextPacket extends DataPacket{

    private String str;
    private Player player;

    public void setPlayer(Player player)
    {
        this.player = player;
    }

    public Player getPlayer()
    {
        return this.player;
    }

    public String getText()
    {
        return this.str;
    }

    public void putString(String str)
    {
        this.str = str;
    }

    @Override
    public byte pid()
    {
        return 1;
    }

    @Override
    public  void encode()
    {

    }

    @Override
    public void decode()
    {

    }

}

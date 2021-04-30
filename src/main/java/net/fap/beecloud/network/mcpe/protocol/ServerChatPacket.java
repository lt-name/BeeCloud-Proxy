package net.fap.beecloud.network.mcpe.protocol;

import net.fap.beecloud.console.ServerLogger;

/**
 * 服务器聊天数据包
 * 当玩家聊天时会触发这个数据包
 *
 * @author catrainbow
 */

public class ServerChatPacket extends BeeCloudPacket{

    public String message;
    public String player;
    public String server;

    public String getPlayer() {
        return player;
    }

    @Override
    public void resend() {
        ServerLogger.info("["+server+"] "+player+">> "+message);
    }

    @Override
    public void putString(String[] pk2) {
        this.server = pk2[1];
        this.player = pk2[2];
        this.message = pk2[3];
    }

    @Override
    public String to_String() {
        return "ServerChatPacket:§8["+server+"]§r"+player+"§8>>§r"+message;
    }

}

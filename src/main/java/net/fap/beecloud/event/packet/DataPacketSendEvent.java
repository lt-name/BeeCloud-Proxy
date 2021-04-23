package net.fap.beecloud.event.packet;

import net.fap.beecloud.event.Event;
import net.fap.beecloud.network.mcpe.protocol.BeeCloudPacket;

public class DataPacketSendEvent extends Event {

    public BeeCloudPacket packet;

    @Override
    public void call() {
        if (beeCloudEvent.listener!=null)
        {
            this.beeCloudEvent.listener.handleEvent(this);
        }
    }

}

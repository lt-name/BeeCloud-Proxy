package net.fap.beecloud.event.player;

import net.fap.beecloud.event.Event;

public class PlayerJoinEvent extends Event {

    public String player;

    public String getPlayer() {
        return this.player;
    }

    @Override
    public void call() {
        if (beeCloudEvent.listener!=null)
        {
            this.beeCloudEvent.listener.handleEvent(this);
        }
    }
}

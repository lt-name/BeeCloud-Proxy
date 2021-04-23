package net.fap.beecloud.event;

public class BeeCloudEvent {

    public Listener listener;

    public void registerListener(Listener listener)
    {
        this.listener = listener;
    }

}

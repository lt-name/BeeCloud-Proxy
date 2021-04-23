package net.fap.beecloud.event;

/**
 * 事件监听器
 *
 * @author catrainbow
 */

public interface Listener {

    public void handleEvent(Event event);
    public void handlePacket(Event event);

}

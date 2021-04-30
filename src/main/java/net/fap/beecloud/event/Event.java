package net.fap.beecloud.event;

/**
 * 在服务器内可能发生的事情叫做事件
 *
 * @author catrainbow
 */

public class Event {

    protected String eventName = null;
    private boolean isCancelled = false;

    public Event() {}

    public String getEventName()
    {
        return this.eventName == null ? this.getClass().getName() : this.eventName;
    }

    public void setCancelled() {
        this.isCancelled = true;
    }

    public void call()
    {
        EventHandler.getListener().call(this);
    }

    public boolean isCancelled()
    {
        return this.isCancelled;
    }

}

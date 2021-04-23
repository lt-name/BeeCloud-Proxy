package net.fap.beecloud.event;

/**
 * 在服务器内发生的事情叫做服务器的事件
 * 能够更加方便的监听和使用一些功能
 *
 * @author catrainbow
 */

public class Event {

    public BeeCloudEvent beeCloudEvent;

    public Event()
    {
        super();
    }

    /**
     * 事件触发函数 call()
     */
    public void call()
    {
    }

    public BeeCloudEvent getBeeCloudEvent() {
        return this.beeCloudEvent;
    }

    public void setBeeCloudEvent(BeeCloudEvent event)
    {
        this.beeCloudEvent = event;
    }

}

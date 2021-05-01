package net.fap.beecloud.plugin;

/**
 * 插件接口类
 *
 * @author catrainbow
 */

public interface PluginCase {

    /**
     * 插件开启方法
     */
    public void onEnable();


    /**
     * 插件加载方法
     */
    public void onLoad();

}

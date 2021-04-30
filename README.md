# BeeCloud-Proxy
## 什么是 BeeCloud(峰云)?
一款基于UDP的Nukkit数据包转发软件，实现多个服务器之间相互沟通
## 怎么使用BeeCloud?
下载我们的项目并构建，或者在 http://ci.fapixel.com:8080 上下载
### SynapseAPI下载
你需要安装BeeCloudNukkit-API在你的子服务器中，这样才能互相沟通
https://github.com/rainbow188/BeeCloudNukkitAPI
## 开发日志
- [x] 多个服务器数据包和自定义数据包通信
- [x] 多个服务器人数同步
- [x] 插件系统 - 面相开发者的BeeCloud环境
- [x] 事件系统 - 事件监听器

## 如何安装插件到你的BeeCloud服务器?
将插件放进beeCloud/plugins文件夹里面并打开BeeCloud生成的
pluginsList.xml文件，写入下面插件配置
```
<?xml version="1.0" encoding="UTF-8"?>
<plugins>


    <!--- <test pattern="注释内容" />
    添加插件的例子
      <plugin>
        <name>BeeCloud实例插件</name>
        <jar>BeeCloud/plugins/Demo.jar</jar>
        <class>net.fap.proxy.demo.Main</class>
    </plugin>
    name是插件的名字
    jar是插件的路径
    class是插件主类的位置
    <test pattern="NTSC" /> -->


</plugins>
```

## 如何编写 BeeCloud-Proxy的插件?
```
package net.fap.beecloud.example;

import net.fap.beecloud.Server;
import net.fap.beecloud.console.ServerLogger;
import net.fap.beecloud.plugin.PluginCase;

/**
 * BeeCloud 实例插件
 *
 * @author catrainbow
 */

//插件必须 implements PluginCase 并调用onLoad和onEnable入口方法
public class Main implements PluginCase {

    /**
     * 当插件被加载时会触发
     */
    public void onLoad()
    {
        ServerLogger.info("开始加载插件 ExamplePlugin");
        Server server = Server.getServer();
    }

    /**
     * 当插件被开启时会触发
     */
    public void onEnable()
    {
        ServerLogger.info("开始开启插件 ExamplePlugin");
    }

}
```

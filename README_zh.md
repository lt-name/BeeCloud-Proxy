# BeeCloud Proxy

基于UDP协议的Minecraft基岩版Nukkit服务器群组软件

[English](README.md)

## 怎么使用
下载服务端并使用命令 "java -jar beecloud(文件名).jar"
去运行它并安装BeeCloudAPI到所有Nukkit子服务器上面

Java CI: http://bbs.fapixel.com:8080
下载 BeeCloudAPI: https://github.com/rainbow188/BeeCloudNukkitAPI

### 实现功能

- 群组服务器人数同步
- 开放的开发环境，可以为BeeCloud自创扩展插件
- 多个Nukkit服务器之间相互发送数据包并进行字符串通信

### 怎么使用 BeeCloud 的插件?

- 放所有插件到 BeeCloud/plugins/ 并运行你的服务器
- 确保你的插件的主包下有plugin.yml而不是在src目录下
```
name:ExamplePlugin
main:net.fap.beecloud.example.Main
```

## 对于开发者

### 开发Nukkit插件
将你的插件依赖到 BeeCloudAPI 并
使用监听器 (@BeeCloudPacketEvent) 去发送
和接收数据包

### 开发 BeeCloud Proxy 插件

#### 主类例子
继承到 PluginBase
并 implements PluginCase
使用函数:
- public void onLoad();
- public void onEnable();
```java
package net.fap.beecloud.example;

import net.fap.beecloud.Server;
import net.fap.beecloud.console.ServerLogger;
import net.fap.beecloud.plugin.PluginBase;
import net.fap.beecloud.plugin.PluginCase;

/**
 * BeeCloud 实例插件
 *
 * @author catrainbow
 */

//插件必须 implements PluginCase 并调用onLoad和onEnable入口方法
//插件可以 extends PluginBase 并使用相关方法
public class Main extends PluginBase implements PluginCase {

    /**
     * 当插件被加载时会触发
     */
    public void onLoad()
    {
        ServerLogger.info("开始加载插件");
        Server server = this.getServer();

        /**
         * 自定义命令
         * @see MyCommand
         */
        server.registerCommand(new MyCommand());

        /**
         * 自定义监听器方法一
         *
         * 覆写核心自带监听器
         * Server.reloadListener(Listener);
         *
         * @see MyListener
         */
        //server.reloadListener(new MyListener());

        /**
         * 自定义监听器方法二
         * 多开监听器方法
         *
         * Server.registerListeners(Listener)
         *
         * @see MyListener
         */
        server.registerListeners(this,new MyListener());



    }

    /**
     * 当插件被开启时会触发
     */
    public void onEnable()
    {
        ServerLogger.info("欢迎使用!!!");
    }

}
```

#### 自定义命令
继承类到 CommandHandler
并使用 "Server.getServer.register(CommandHandler);" 注册命令
```java
package net.fap.beecloud.example;

import net.fap.beecloud.console.ServerLogger;
import net.fap.beecloud.console.simple.CommandHandler;

/**
 * 自定义命令
 */

public class MyCommand extends CommandHandler {

    public MyCommand()
    {
        commandStr = "mycmd";
        commandUsage = "自定义命令";
        this.setCommandStr(commandStr,commandUsage);
    }

    @Override
    public void setCommandStr(String commandStr, String commandUsage) {
        super.setCommandStr(commandStr, commandUsage);
    }

    @Override
    public void runCommand() {

        ServerLogger.info("自定义命令执行成功");

        super.runCommand();
    }
}
```

#### 自定义监听器
```java
package net.fap.beecloud.example;

import net.fap.beecloud.console.ServerLogger;
import net.fap.beecloud.event.Event;
import net.fap.beecloud.event.Listener;
import net.fap.beecloud.event.player.PlayerJoinEvent;

/**
 * 自定义监听器类
 * 用于监听服务器事件
 */

public class MyListener implements Listener {

    public void call(Event event)
    {
      if (event instanceof PlayerJoinEvent)
        {
            SynapsePlayer player = SynapsePlayer.getPlayer(((PlayerJoinEvent) event).getPlayer());
            player.sendMessage("§c欢迎来到我们服务器!!!");
            player.sendTitle("§c生存服务器","§b大区1",2,200,2);
        }
    }

}
```
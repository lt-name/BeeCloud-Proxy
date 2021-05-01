# BeeCloud Proxy

BeeCloud Minecraft Bedrock Edition proxy server software with UDP protocol.

[简体中文](README_zh.md)

## How to use? 
Download the proxy file and use "java -jar beecloud(fileName).jar"
to run the proxy.Download BeeCloudAPI plugin and put it into your server.

Java CI: http://bbs.fapixel.com:8080
Download BeeCloudAPI: https://github.com/rainbow188/BeeCloudNukkitAPI

### Implement functions

- The number of multiple Nukkit servers is synchronized.
- Develop BeeCloud Proxy's own plug-in.
- A plurality of Nukkit servers send DataPacket to each other and carry out string communication.

### How to use BeeCloud plugins?

- put all plugins into BeeCloud/plugins/ and run your proxy server.
- Make sure that your plugin has a plugin.yml file in your first packege folder but not src folder.
```
name:ExamplePlugin
main:net.fap.beecloud.example.Main
```

## For developer

### Plugin for Nukkit
Depend your Nukkit plugins on BeeCloudAPI and
use Event Listener (@BeeCloudPacketEvent) to send packet
and get back the packet.

### Plugin for BeeCloud Proxy
ExamlplePlugin: https://github.com/rainbow188/ExamplePlugin
#### Example Main class
Extends your main class with PluginBase
and implements PluginCase
Use function:
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

#### Custom Command
Extends your class with CommandHandler
And register it by "Server.getServer.register(CommandHandler);"
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

#### Custom Listener
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
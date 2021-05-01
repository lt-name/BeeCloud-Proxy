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
将插件放进beeCloud/plugins文件夹里面,并且保证在插件的第一个目录下(不是根目录)
有一个plugin.yml文件，里面有name(插件名字),main(主类路径),否则不会加载插件
示例插件: https://github.com/rainbow188/ExamplePlugin
```
name:ExamplePlugin
main:net.fap.beecloud.example.Main
```

## 我是Nukkit开发者,怎么开发插件?
将你的Nukkit插件依赖到BeeCloudNukkitAPI上
并通过Synapse类的send方法发送数据包
通过BeeCloudPacketEvent回收数据包
进行服务器之间的通信

## 如何编写 BeeCloud-Proxy的插件?
### 主类继承PluginBase并implements PluginCase接口
```Java
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

### 自定义命令
将命令继承到CommandHandler并通过Server.registerCommand(CommandHandler);进行注册
```Java
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
### 自定义监听器
```Java
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

package net.fap.beecloud.plugin;

import net.fap.beecloud.Server;
import net.fap.beecloud.console.ServerLogger;
import net.fap.beecloud.utils.XMLParser;

import java.util.List;

public class PluginLoader {

    public static void loadPlugin(Server server)
    {
        try
        {
            List<PluginBase> pluginList = XMLParser.getPluginList();
            PluginManager pluginManager = new PluginManager(pluginList);
            for(PluginBase plugin : pluginList)
            {
                PluginCase pluginLoader = pluginManager.getInstance(plugin.getClassName());
                ServerLogger.waring("Loading plugin "+plugin.getName());
                pluginLoader.onLoad();
                ServerLogger.waring("Enable plugin "+plugin.getName());;
                pluginLoader.onEnable();
                PluginBase.pluginMap.add(plugin.getName());
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

}

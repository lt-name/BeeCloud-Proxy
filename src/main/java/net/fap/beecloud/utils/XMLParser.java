package net.fap.beecloud.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.fap.beecloud.plugin.PluginBase;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class XMLParser
{
    public static List<PluginBase> getPluginList() throws DocumentException
    {
        List<PluginBase> list = new ArrayList<>();

        SAXReader saxReader =new SAXReader();
        Document document = saxReader.read(new File("pluginList.xml"));
        Element root = document.getRootElement();
        List<?> plugins = root.elements("plugin");
        for(Object pluginObj : plugins)
        {
            Element pluginEle = (Element)pluginObj;
            PluginBase plugin = new PluginBase();
            plugin.setName(pluginEle.elementText("name"));
            plugin.setJar(pluginEle.elementText("jar"));
            plugin.setClassName(pluginEle.elementText("class"));
            list.add(plugin);
        }

        return list;
    }
}
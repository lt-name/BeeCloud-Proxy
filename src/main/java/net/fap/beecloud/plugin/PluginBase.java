package net.fap.beecloud.plugin;

import java.util.ArrayList;

public class PluginBase {

    public static ArrayList<String> pluginMap = new ArrayList<>();

    private String name;

    private String jar;

    private String className;

    public void setClassName(String className) {
        this.className = className;
    }

    public void setJar(String jar) {
        this.jar = jar;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJar() {
        return jar;
    }

    public String getClassName() {
        return className;
    }

    public String getName() {
        return name;
    }
}

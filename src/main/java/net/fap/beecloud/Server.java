package net.fap.beecloud;

import cn.nukkit.network.protocol.DataPacket;
import net.fap.beecloud.console.ServerLogger;
import net.fap.beecloud.console.simple.*;
import net.fap.beecloud.event.BeeCloudListener;
import net.fap.beecloud.event.Event;
import net.fap.beecloud.event.EventHandler;
import net.fap.beecloud.event.Listener;
import net.fap.beecloud.event.player.PlayerJoinEvent;
import net.fap.beecloud.network.Packet;
import net.fap.beecloud.network.mcpe.protocol.BeeCloudPacket;
import net.fap.beecloud.plugin.PluginLoader;
import net.fap.beecloud.utils.Shutdown;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class Server {

    public static String ENCODING_UTF8 = "UTF-8";
    public static String ENCODING_GBK = "GBK";
    public static String ENCODING_GB2312 = "GB2312";

    private static Server server;

    public int port1;
    public int port2;

    public String clientPassword;

    private String serverPath = String.valueOf(System.getProperty("user.dir"));
    private File config = new File(this.getDataPath()+"/server.properties");
    private File pluginData = new File(this.getDataPath()+"/plugins");
    private File pluginList = new File(this.getDataPath()+"/pluginList.xml");

    public static ArrayList<SynapsePlayer> onLinePlayerList = new ArrayList<>();

    public Server()
    {
        createConfig();
        if (!pluginData.exists()) pluginData.mkdir();
        this.port1 = Integer.parseInt(this.getConfigValue("server-port"));
        this.port2 = this.port1+1;
        this.clientPassword = this.getConfigValue("synapse-password");
    }

    public void init() {


        ServerLogger.info("- BeeCloud Proxy Start -");

        server = this;

        ServerLogger.waring("- Running your server on: "+this.port1+" -");

        CommandHandler.registerCommand(new HelpCommand());
        CommandHandler.registerCommand(new ListCommand());
        CommandHandler.registerCommand(new VersionCommand());
        CommandHandler.registerCommand(new PluginListCommand());
        CommandHandler.registerCommand(new StopCommand());

        EventHandler.setListener(new BeeCloudListener());

        ServerLogger.info("- Enabling plugins... -");
        PluginLoader.loadPlugin(this);

        Shutdown.shutdownTask();

        ServerLogger.info("- Done! For help type /help");

        try {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        receive();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try{
                        while (true)
                        {
                            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                            String commandStr = null;
                            while ((commandStr=br.readLine())!=null)
                                CommandHandler.handleCommand(commandStr);
                        }
                    }catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                  }
            }).start();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void receive() throws IOException {
        DatagramSocket ds = new DatagramSocket(port1);
        while (true) {
            byte[] bytes = new byte[1024];
            DatagramPacket dp = new DatagramPacket(bytes, bytes.length);
            ds.receive(dp);
            String pk1 = new String(dp.getData(), 0, dp.getLength(),ENCODING_UTF8);
            //String pk2 = new String(pk1.getBytes(ENCODING_GBK), ENCODING_GBK);
            Packet.handlePacket(pk1);
        }
    }

    public void send(DataPacket dataPacket){
        try{
                DatagramSocket ds = new DatagramSocket();
                byte[] bytes = dataPacket.toString().getBytes(ENCODING_UTF8);
                InetAddress address =InetAddress.getByName("127.0.0.1");
                DatagramPacket dp = new DatagramPacket(bytes,bytes.length,address,port2);
                ds.send(dp);
                ds.close();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static Server getServer()
    {
        return server;
    }

    public int getOnlinePlayerCount()
    {
        return onLinePlayerList.size();
    }

    public ArrayList<SynapsePlayer> getOnLinePlayer()
    {
        return onLinePlayerList;
    }

    public boolean isPlayerOnline(SynapsePlayer player)
    {
        return onLinePlayerList.contains(player);
    }

    public boolean isPlayerOnLine(String player)
    {
        for (SynapsePlayer pl : onLinePlayerList)
            if (pl.player.equals(player))
                return true;
        return false;
    }

    public void send(BeeCloudPacket dataPacket)
    {
        try{
            DatagramSocket ds = new DatagramSocket();
            dataPacket.resend();
            String pk2 = new String(dataPacket.to_String().getBytes(ENCODING_UTF8), ENCODING_UTF8);
            byte[] bytes = pk2.getBytes(ENCODING_UTF8);
            InetAddress address =InetAddress.getByName("127.0.0.1");
            DatagramPacket dp = new DatagramPacket(bytes,bytes.length,address,port2);
            ds.send(dp);
            ds.close();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private void createConfig()
    {
        if (!config.exists())
        {
            writeData(config,"#Properties Config File");
            writeData(config,"server-port=8888");
            writeData(config,"server-ip=0.0.0.0");
            writeData(config,"synapse-password=123456789");
        }
        if (!pluginList.exists())
        {
            writeData(pluginList,"<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                    "<plugins>\n" +
                    "    <!--- <test pattern=\"例子\" />\n" +
                    "    添加插件的例子\n" +
                    "      <plugin>\n" +
                    "        <name>BeeCloud实例插件</name>\n" +
                    "        <jar>BeeCloud/plugins/Demo.jar</jar>\n" +
                    "        <class>net.fap.proxy.demo.Main</class>\n" +
                    "    </plugin>\n" +
                    "    name是插件的名字\n" +
                    "    jar是插件的路径\n" +
                    "    class是插件主类的位置\n" +
                    "    <test pattern=\"NTSC\" /> -->\n" +
                    "</plugins>");
        }
    }

    public String getDataPath()
    {
        return this.serverPath;
    }

    public String getConfigValue(String index)
    {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(this.getDataPath()+"\\server.properties")));
            String lineData = null;
            while ((lineData = br.readLine()) != null) {
                String[] str1 = lineData.split("\\=");
                if (str1[0].equals(index)) return str1[1];
            }
        }catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    private void writeData(File file,String data)
    {
        try{
            if (!file.exists()) file.createNewFile();
            BufferedWriter bufferedWriter = new BufferedWriter (new OutputStreamWriter (new FileOutputStream (file,true),"UTF-8"));
            bufferedWriter.write(data);
            bufferedWriter.newLine();;
            bufferedWriter.close();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}

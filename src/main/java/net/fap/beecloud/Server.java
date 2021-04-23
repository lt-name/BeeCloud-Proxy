package net.fap.beecloud;

import cn.nukkit.network.protocol.DataPacket;
import net.fap.beecloud.console.ServerLogger;
import net.fap.beecloud.console.simple.CommandHandler;
import net.fap.beecloud.console.simple.HelpCommand;
import net.fap.beecloud.console.simple.ListCommand;
import net.fap.beecloud.console.simple.VersionCommand;
import net.fap.beecloud.network.Packet;
import net.fap.beecloud.network.mcpe.protocol.BeeCloudPacket;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class Server {

    public int port1;
    public int port2;

    public String clientPassword;

    private String serverPath = String.valueOf(System.getProperty("user.dir"));
    private File config = new File(this.getDataPath()+"\\server.properties");

    public static ArrayList<SynapsePlayer> onLinePlayerList = new ArrayList<>();

    public Server()
    {
        createConfig();
        this.port1 = Integer.parseInt(this.getConfigValue("server-port"));
        this.port2 = this.port1+1;
        this.clientPassword = this.getConfigValue("synapse-password");
    }

    public void init() {


        ServerLogger.info("- BeeCloud Proxy Start -");
        ServerLogger.waring("- Running your server on: "+this.port1+" -");

        CommandHandler.registerCommand(new HelpCommand());
        CommandHandler.registerCommand(new ListCommand());
        CommandHandler.registerCommand(new VersionCommand());

        ServerLogger.info("- For help type /help");


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
            String pk1 = new String(dp.getData(), 0, dp.getLength());
            Packet.handlePacket(pk1);
        }
    }

    public void send(DataPacket dataPacket){
        try{
                DatagramSocket ds = new DatagramSocket();
                byte[] bytes = dataPacket.toString().getBytes();
                InetAddress address =InetAddress.getByName("127.0.0.1");
                DatagramPacket dp = new DatagramPacket(bytes,bytes.length,address,port2);
                ds.send(dp);
                ds.close();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void send(BeeCloudPacket dataPacket)
    {
        try{
            DatagramSocket ds = new DatagramSocket();
            dataPacket.resend();
            byte[] bytes = dataPacket.to_String().getBytes();
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
            writeData("#Properties Config File");
            writeData("server-port=8888");
            writeData("server-ip=0.0.0.0");
            writeData("synapse-password=123456789");
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

    private void writeData(String data)
    {
        try{
            if (!config.exists()) config.createNewFile();
            FileWriter fileWriter = new FileWriter(config,true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(data);
            bufferedWriter.newLine();;
            bufferedWriter.close();
            fileWriter.close();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }


}

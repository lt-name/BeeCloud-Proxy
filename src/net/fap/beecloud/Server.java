package net.fap.beecloud;

import cn.nukkit.network.protocol.DataPacket;
import cn.nukkit.network.protocol.MovePlayerPacket;
import net.fap.beecloud.console.ServerLogger;
import net.fap.beecloud.console.simple.CommandHandler;
import net.fap.beecloud.console.simple.HelpCommand;
import net.fap.beecloud.console.simple.ListCommand;
import net.fap.beecloud.network.Packet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Server {

    public int port1; //get packet link
    public int port2; //send packet link

    public static ArrayList<SynapsePlayer> onLinePlayerList = new ArrayList<>();

    public Server(int port)
    {
        this.port1 = port;
        port2 = port1+1;
    }

    public void init() {


        ServerLogger.info("- BeeCloud Proxy Start -");

        CommandHandler.registerCommand(new HelpCommand());
        CommandHandler.registerCommand(new ListCommand());

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


}

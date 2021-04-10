package net.fap.beecloud;

import cn.nukkit.network.protocol.DataPacket;
import cn.nukkit.network.protocol.MovePlayerPacket;
import net.fap.beecloud.network.Packet;

import java.io.IOException;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Server {

    public int port1; //get packet link
    public int port2; //send packet link

    public Server(int port)
    {
        this.port1 = port;
        port2 = port1+1;
    }

    public void init() {
        info("- BeeCloud Proxy Start -");
        info("- For help type /help");
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
            net.fap.beecloud.network.mcpe.DataPacket dataPacket = Packet.handlePacket(pk1);

        }
    }

    private void send(DataPacket dataPacket) throws IOException {
        try{
            if (dataPacket instanceof MovePlayerPacket)
            { ;
                DatagramSocket ds = new DatagramSocket();
                byte[] bytes = dataPacket.toString().getBytes();
                InetAddress address =InetAddress.getByName("127.0.0.1");
                DatagramPacket dp = new DatagramPacket(bytes,bytes.length,address,port2);
                ds.send(dp);
                ds.close();
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }



    public void info(String message)
    {
        System.out.println(getTime()+"[Server] "+message);
    }

    private String getTime()
    {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return "["+sdf.format(date)+"]";
    }

}

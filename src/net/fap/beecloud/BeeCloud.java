package net.fap.beecloud;

import net.fap.beecloud.client.TestClient;

public class BeeCloud {

    public static Server server;

    public static void main(String[] args)
    {
        server = new Server(8888);
        server.init();
    }

}

package net.fap.beecloud;

import net.fap.beecloud.client.TestClient;

public class BeeCloud {

    public static void main(String[] args)
    {
        Server server = new Server(8888);
        server.init();
        //new TestClient(8888); 测试方法
    }

}

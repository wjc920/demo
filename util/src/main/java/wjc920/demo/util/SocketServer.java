package wjc920.demo.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {
    
    private static ServerSocket server = null;
    private static Socket socket = null;
    private static PrintWriter writer = null;
    static {
        try {
            server = new ServerSocket(Consts.SOCKET_PORT);
            socket = server.accept();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void destory() {
        if(server != null) {
            synchronized (SocketServer.class) {
                if(server!=null) {
                    try {
                        server.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        
        if(writer!=null) {
            synchronized (SocketServer.class) {
                if(writer!=null) {
                    writer.close();
                    writer = null;
                }
            }
        }
        //TODO 
//        if(wri)
    }
    
    

}

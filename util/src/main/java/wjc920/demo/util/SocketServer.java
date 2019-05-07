package wjc920.demo.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public abstract class SocketServer extends Thread {

    private ServerSocket server;
    private Socket socket;
    private PrintWriter writer;
    private long maxTime;

    public SocketServer(long maxTime) {
        this.maxTime = maxTime;
    }

    @Override
    public void run() {
        try {
            server = new ServerSocket(Consts.SOCKET_PORT);
            socket = server.accept();
            writer = new PrintWriter(socket.getOutputStream());
            Message msg = null;
            while (!server.isClosed()) {
                if(System.currentTimeMillis() > maxTime) {
                    close();
                }
                msg = createMsg();
                Thread.sleep(msg.getDelay());
                writer.println(msg.getBody());
                writer.flush();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        
        if (server != null) {
            synchronized (SocketServer.class) {
                if (server != null) {
                    try {
                        server.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        
        if (socket != null) {
            synchronized (SocketServer.class) {
                if (socket != null) {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    }

    public abstract Message createMsg();

}

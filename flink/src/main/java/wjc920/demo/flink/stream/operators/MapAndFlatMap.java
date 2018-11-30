package wjc920.demo.flink.stream.operators;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

public class MapAndFlatMap {
    
    
    public static void main(String[] args) {
        int port = 5677;
        new Thread(() -> startServerSocket(port), "socket-server").start();
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStream<String> source = env.socketTextStream("localhost", port);
        
        
        
    }
    
    
    
    
    
    
    private static void startServerSocket(int port) {
        ServerSocket server = null;
        Socket socket = null;
        PrintWriter writer = null;
        try {
            server = new ServerSocket(port);
            socket = server.accept();
            writer = new PrintWriter(socket.getOutputStream());
            
            writer.flush();
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                writer.close();
            }
            try {
                if (socket != null) {
                    socket.close();
                }
                if (server != null) {
                    server.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}

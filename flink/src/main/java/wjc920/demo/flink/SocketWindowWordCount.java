package wjc920.demo.flink;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.flink.api.common.JobExecutionResult;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;

public class SocketWindowWordCount {

    public static void main(String[] args) {
        int port = 5677;
        startServerSocket(port);
        final StreamExecutionEnvironment env=StreamExecutionEnvironment.getExecutionEnvironment();
        DataStream<String> source=env.socketTextStream("localhost", port);
        DataStream<WordWithCount> windowCounts = source.flatMap(new FlatMapFunction<String, WordWithCount>() {
            @Override
            public void flatMap(String value, Collector<WordWithCount> out) throws Exception {
                for(String word:value.split("\\s")) {
                    out.collect(new WordWithCount(word, 1));
                }
            }
        }).ke;
    }

    public static class WordWithCount {

        public String word;
        public long count;

        public WordWithCount() {
        }

        public WordWithCount(String word, long count) {
            this.word = word;
            this.count = count;
        }

        @Override
        public String toString() {
            return word + " : " + count;
        }
    }

    private static void startServerSocket(int port) {
        try {
            ServerSocket server = new ServerSocket(port);
            Socket socket = server.accept();
            PrintWriter writer = new PrintWriter(socket.getOutputStream());
            writer.println("flink pig");
            writer.println("cat dog cat");
            writer.println("tiger pig");
            writer.flush();
            writer.close();
            socket.getOutputStream().close();
            socket.close();
            server.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

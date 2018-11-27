package wjc920.demo.flink;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.functions.ReduceFunction;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.util.Collector;

public class SocketWindowWordCount {

    public static void main(String[] args) throws Exception {
        int port = 5677;
        new Thread(() -> startServerSocket(port)).start();
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStream<String> source = env.socketTextStream("localhost", port);
        DataStream<WordWithCount> windowCounts = source.flatMap(new FlatMapFunction<String, WordWithCount>() {

            private static final long serialVersionUID = 3068103555661117367L;

            @Override
            public void flatMap(String value, Collector<WordWithCount> out) throws Exception {
                for (String word : value.split("\\s")) {
                    out.collect(new WordWithCount(word, 1));
                }
            }
        }).keyBy("word").timeWindow(Time.seconds(5), Time.seconds(1))
                .reduce(new ReduceFunction<SocketWindowWordCount.WordWithCount>() {
                    private static final long serialVersionUID = -8747078707093052174L;

                    @Override
                    public WordWithCount reduce(WordWithCount a, WordWithCount b) throws Exception {
                        return new WordWithCount(a.word, a.count + b.count);
                    }
                });
        windowCounts.print().setParallelism(1);

        env.execute("Socket Window WordCount");
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
        ServerSocket server = null;
        Socket socket = null;
        PrintWriter writer = null;
        try {
            server = new ServerSocket(port);
            socket = server.accept();
            writer = new PrintWriter(socket.getOutputStream());
            writer.println("flink pig");
            writer.println("cat dog cat");
            writer.println("tiger pig");
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

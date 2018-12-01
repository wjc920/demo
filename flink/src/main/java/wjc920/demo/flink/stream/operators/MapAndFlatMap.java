package wjc920.demo.flink.stream.operators;

import java.util.Random;

import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import wjc920.demo.util.Consts;
import wjc920.demo.util.Message;
import wjc920.demo.util.SocketServer;

public class MapAndFlatMap {

    public static SocketServer server;

    public static void main(String[] args) throws Exception {
        startServer();
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStream<String> source = env.socketTextStream("localhost", Consts.SOCKET_PORT);
        source.map((in) -> new Tuple2<>(Integer.parseInt(in.split("\\s")[0]), Integer.parseInt(in.split("\\s")[1]))).returns(Types.TUPLE(Types.INT, Types.INT))
        .filter((arr) -> arr.f0 > 3)
        .setParallelism(1).print();
        env.execute();
    }

    public static void startServer() {
        Random r = new Random();
        server = new SocketServer(System.currentTimeMillis() + 100 * 1000) {
            @Override
            public Message createMsg() {
                return new Message(r.nextInt(5) + " " + r.nextInt(50), (long) r.nextInt(100));
            }
        };
        server.start();
    }

}

package wjc920.demo.java.flink.stream.operators;

import java.util.Random;

import org.apache.flink.api.common.functions.AggregateFunction;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.assigners.TumblingProcessingTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.util.Collector;

import wjc920.demo.java.util.Consts;
import wjc920.demo.java.util.Message;
import wjc920.demo.java.util.SocketServer;

public class OperatorsDemo {

    public static SocketServer server;

    public static void main(String[] args) throws Exception {
        startServer();
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStream<String> source = env.socketTextStream("localhost", Consts.SOCKET_PORT);
//        sumAll(mapedDateStream);
//        getAverage(map(source));
        flatMap(source);
        env.execute();
    }
    
    public static DataStream<Tuple2<Integer, Integer>> map(DataStream<String> source){
        return source.map((in) -> new Tuple2<>(Integer.parseInt(in.split("\\s")[0]), Integer.parseInt(in.split("\\s")[1])))
                .returns(Types.TUPLE(Types.INT,Types.INT));
    }
    
    public static void flatMap(DataStream<String> source) {
        source.flatMap(new FlatMapFunction<String, String>() {
            @Override
            public void flatMap(String value, Collector<String> out) throws Exception {
                /*该方法内部，同样可以选择像map函数一样输出一个Tuple，感觉flatMap能实现所有map的功能 ???*/
                for (String str : value.split("\\s")) {
                    out.collect(str);
                }
            }
        }).print();
    }
    
    public static void sumAll(DataStream<Tuple2<Integer, Integer>> mapedDateStream) {
        mapedDateStream.windowAll(TumblingProcessingTimeWindows.of(Time.seconds(5))).sum(0).print();
    }
    
    public static void getAverage(DataStream<Tuple2<Integer, Integer>> mapedDateStream) {
        mapedDateStream.windowAll(TumblingProcessingTimeWindows.of(Time.seconds(5))).aggregate(new AverageAggregate()).print();
    }
    public static class AverageAggregate implements AggregateFunction<Tuple2<Integer, Integer>, Tuple2<Integer, Integer>, Double>{

        private static final long serialVersionUID = -5775376898292912007L;

        @Override
        public Tuple2<Integer, Integer> createAccumulator() {
            return new Tuple2<Integer, Integer>(0, 0);
        }

        @Override
        public Tuple2<Integer, Integer> add(Tuple2<Integer, Integer> value, Tuple2<Integer, Integer> accumulator) {
            return new Tuple2<Integer, Integer>(accumulator.f0 + value.f0, accumulator.f1 + value.f1);
        }

        @Override
        public Double getResult(Tuple2<Integer, Integer> accumulator) {
            return accumulator.f1 * 1.0 / accumulator.f0;
        }

        @Override
        public Tuple2<Integer, Integer> merge(Tuple2<Integer, Integer> a, Tuple2<Integer, Integer> b) {
            return new Tuple2<Integer, Integer>(a.f0 + b.f0, a.f1 + b.f1);
        }
        
    }

    public static void startServer() {
        Random r = new Random();
        server = new SocketServer(System.currentTimeMillis() + 100 * 1000) {
            @Override
            public Message createMsg() {
                return new Message(r.nextInt(5) + " " + r.nextInt(50), (long) r.nextInt(10));
            }
        };
        server.start();
    }
    
    static String[] wordArr = {"whole anatomy respective as a whole discrete evangelist","mutex respective whole discrete anonymous respective whole encapsulate"};

}

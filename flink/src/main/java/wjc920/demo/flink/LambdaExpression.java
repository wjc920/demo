package wjc920.demo.flink;


import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;

public class LambdaExpression {

    public static void main(String[] args) throws Exception {
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
//        map(env);
//        flatMap(env);
        genericMap(env);
        env.execute();
    }

    /**
     * eg0
     * @param env
     */
    private static void map(StreamExecutionEnvironment env) {
        env.fromElements(1, 2, 3).map((Integer i) -> {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < i; j++) {
                sb.append("a");
            }
            return sb.toString();
        }).print();
    }
    
    /* eg1 和 eg2 中，必须加返回类型声明(returns)，Flink不提供对泛型的运行时推断。*/
    

    /**
     * eg1
     * @param env
     */
    private static void flatMap(StreamExecutionEnvironment env) {
        env.fromElements(1, 2, 3).flatMap((Integer i, Collector<String> out) -> {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < i; j++) {
                sb.append("a");
                out.collect(sb.toString());
            }
        }).returns(Types.STRING).print();
    }
    
    /**
     * eg2
     * @param env
     */
    private static void genericMap(StreamExecutionEnvironment env) {
        env.fromElements(1, 2, 3)
        .map(i -> Tuple2.of(i, i))    // no information about fields of Tuple2
        .returns(Types.TUPLE(Types.INT,Types.INT)).print();
    }

    
}

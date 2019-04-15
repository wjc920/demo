package wjc920.demo.java.flink;

import java.util.Properties;
import java.util.stream.IntStream;

import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer08;
import org.apache.flink.streaming.util.serialization.DeserializationSchema;

public class KafkaConnector {
    
    public static void main(String[] args) {
        StreamExecutionEnvironment env=StreamExecutionEnvironment.getExecutionEnvironment();
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", "localhost:9092");
        // only required for Kafka 0.8
        properties.setProperty("zookeeper.connect", "localhost:2181");
        properties.setProperty("group.id", "test");
        FlinkKafkaConsumer08<String> kafkaConsumer=new FlinkKafkaConsumer08("topic", (DeserializationSchema) new SimpleStringSchema(), properties);
        DataStream<String> stream = env.addSource(kafkaConsumer);
        
        
    }

}

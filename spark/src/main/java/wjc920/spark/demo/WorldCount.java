package wjc920.spark.demo;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

public class WorldCount {
    
    public static void main(String[] args) {
        SparkConf sparkConf = new SparkConf().setAppName(WorldCount.class.getSimpleName()).setMaster("local");
        JavaSparkContext ctx = new JavaSparkContext(sparkConf);
        JavaRDD<String> in = ctx.textFile("D://in.txt");         
        JavaRDD<String> fromTos = in.filter(line -> line.contains("上海_重庆"));
        System.out.println(fromTos.count());
    }

}

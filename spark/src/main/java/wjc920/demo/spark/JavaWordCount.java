package wjc920.demo.spark;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

import java.util.Arrays;

public class JavaWordCount {
    public static void main(String[] args) {
        SparkConf sparkConf = new SparkConf().setAppName("JavaWordCount");
        sparkConf.setMaster("local[2]");
        JavaSparkContext jsc = new JavaSparkContext(sparkConf);
        JavaRDD<String> lines = jsc.textFile("in.txt");
        JavaRDD<String> words = lines.flatMap((line) -> Arrays.asList(line.split(" ")).iterator());
        JavaPairRDD<String, Integer> counts = words.mapToPair((word) -> new Tuple2<>(word, 1)).reduceByKey((x, y) -> x + y);
        counts.saveAsTextFile("out");
    }
}

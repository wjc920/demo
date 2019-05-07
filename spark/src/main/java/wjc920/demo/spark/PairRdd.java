package wjc920.demo.spark;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class PairRdd {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setMaster("local").setAppName("PairRdd");
        JavaSparkContext sc = new JavaSparkContext(conf);
        sc.setLogLevel("WARN");
        JavaPairRDD<String, Integer> rdd1 = sc.parallelizePairs(
                Arrays.asList(
                        new Tuple2<>("a", 1), new Tuple2<>("a", 2),
                        new Tuple2<>("b", 3), new Tuple2<>("c", 4)
                )
        );
        JavaPairRDD<String, Integer> rdd2 = sc.parallelizePairs(
                Arrays.asList(
                        new Tuple2<>("a", 3)
                )
        );
        List<Integer> out = rdd1.values().collect();
        System.out.println("rdd1 values:");
        out.forEach((t) -> System.out.printf("%d,", t));
        System.out.println();
        System.out.println("rdd1 sorted desc:");
        Map<String, Integer> out1 = rdd1.sortByKey(new SelComparator()).collectAsMap();
        out1.forEach((k, v) -> System.out.printf("%s,%d\n", k, v));
        System.out.println("rdd1 join rdd2 desc:");
        List<Tuple2<String, Tuple2<Integer, Integer>>> out2 = rdd1.join(rdd2).collect();
        out2.forEach((t)-> System.out.printf("%s, (%d, %d)\n", t._1, t._2._1, t._2._1));
    }
    static class SelComparator implements Comparator<String>,Serializable{
        @Override
        public int compare(String o1, String o2) {
            return o2.compareTo(o1);
        }
    }
}

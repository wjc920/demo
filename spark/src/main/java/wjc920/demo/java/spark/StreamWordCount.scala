package wjc920.demo.java.spark

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

object StreamWordCount {

  def main(args: Array[String]): Unit = {
    if(args.length<1){
      System.err.println("Usage: StreamingWordCount <directory>")
      return System.exit(1)
    }
    val sparkCfg = new SparkConf().setAppName("WordCount").setMaster("local[2]")
    val ssc = new StreamingContext(sparkCfg, Seconds(20))
    val lines = ssc.textFileStream(args(0))
    val words = lines.flatMap(_.split(" "))
    val wordCounts = words.map(x=>(x,1)).reduceByKey(_+_)
    wordCounts.print()
    ssc.start()
    ssc.awaitTermination()
  }

}

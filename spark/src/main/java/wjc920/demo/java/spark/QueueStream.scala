package wjc920.demo.java.spark

import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD
import org.apache.spark.streaming.{Seconds, StreamingContext}

import scala.collection.mutable

object QueueStream {

  def main(args: Array[String]): Unit = {
    val sparkCfg = new SparkConf().setAppName("QueueStream").setMaster("local[2]")
    val ssc = new StreamingContext(sparkCfg, Seconds(1))
    ssc.sparkContext.setLogLevel("WARN")
    val queueRdd = new mutable.SynchronizedQueue[RDD[Int]]()
    val inputStream = ssc.queueStream(queueRdd)
    val modCounts = inputStream.map(x=>(x % 10,1))
    val reduceStream = modCounts.reduceByKey(_+_)
    reduceStream.print()
    ssc.start()
    for (i <- 1 to 30) {
      queueRdd+=ssc.sparkContext.makeRDD(1 to 3000,10)
      Thread.sleep(1000)
    }
    ssc.stop()
  }

}

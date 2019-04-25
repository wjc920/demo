package wjc920.demo.java.spark

import org.apache.spark.{HashPartitioner, SparkConf}
import org.apache.spark.rdd.RDD
import org.apache.spark.streaming.{Seconds, StreamingContext}

import scala.collection.immutable._
import scala.collection.mutable

/**
 * reduceByKey,
 * 
 * @author jichao.wang
 * @date 2019年04月16日 上午11:22:28 
 */
object QueueStream {

  def main(args: Array[String]): Unit = {
//    val sparkCfg = new SparkConf().setAppName("QueueStream").setMaster("local[2]")
//    val ssc = new StreamingContext(sparkCfg, Seconds(1))
//    ssc.sparkContext.setLogLevel("WARN")
////    ssc.checkpoint("/checkpoint/")
//    val queueRdd = new mutable.SynchronizedQueue[RDD[Int]]()
//    val inputStream = ssc.queueStream(queueRdd)
//    val modCounts = inputStream.map(x => (x % 10, 1))
//    val reduceStream = modCounts.reduceByKey(_ + _)
//    val allReduceStream = reduceStream.updateStateByKey[Int]((curValues: Seq[Int] , preSum: Option[Int]) => {
//      Some(curValues.sum + preSum.getOrElse(0))
//    })
//
//    reduceStream.print()
//    ssc.start()
//    for (i <- 1 to 30) {
//      queueRdd += ssc.sparkContext.makeRDD(1 to 3000, 10)
//      Thread.sleep(1000)
//    }
//    ssc.stop()
  }

}

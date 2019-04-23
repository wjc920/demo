package wjc920.demo.java.scala

import java.text.DateFormat
import java.util.Date

object ChineseDate {

  def main(args: Array[String]): Unit = {
    val now = new Date
    val df = DateFormat.getDateInstance(DateFormat.LONG)
    println(df format now)
  }

}

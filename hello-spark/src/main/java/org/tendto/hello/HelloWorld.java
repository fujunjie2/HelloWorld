package org.tendto.hello;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

import java.util.Arrays;

public class HelloWorld {

    public static void main(String[] args) {
        String inputFile = "input/word.txt";
        String outputFile = "file:///E:\\WorkSpace\\Java\\out";

        // 创建Spark配置
        SparkConf conf = new SparkConf()
                .setAppName("Word Count Demo")
                .setMaster("local")
                ;

        // 创建Spark上下文
        JavaSparkContext sc = new JavaSparkContext(conf);

        // 读取文本文件并创建RDD
        JavaRDD<String> input = sc.textFile(inputFile);

        // 对每行文本进行拆分和词频统计
        JavaRDD<String> words = input.flatMap(line -> Arrays.asList(line.split(" ")).iterator());
        JavaPairRDD<String, Integer> counts = words.mapToPair(word -> new Tuple2<>(word, 1))
                .reduceByKey(Integer::sum);

        // 将结果写入到HDFS中
//        counts.saveAsTextFile(outputFile);
//        System.out.println(1);


        // 关闭Spark上下文
        sc.close();
    }
}

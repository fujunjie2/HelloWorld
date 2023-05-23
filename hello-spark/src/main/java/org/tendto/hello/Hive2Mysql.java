package org.tendto.hello;

import org.apache.spark.SparkConf;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import java.util.Properties;

public class Hive2Mysql {

    public static void main(String[] args) {
        // 设置Spark配置
        SparkConf conf = new SparkConf()
                .setAppName("HiveToMySQL")
                .setMaster("http://192.168.22.114")
                ;
        SparkSession spark = SparkSession.builder().config(conf)
                .config("spark.sql.warehouse.dir", "hdfs://node03:8020/home/cdh/hive/warehouse")
                .config("hive.metastore.uris", "thrift://node01:9083")
                .enableHiveSupport().getOrCreate();

        // 读取Hive表数据
        Dataset<Row> dataset = spark.sql("SELECT * from railway.dm_booking_tickets_summary");

        // 创建MySQL连接
//        String url = "jdbc:mysql://192.168.22.213:3306/maas_railway";
//        String user = "root";
//        String password = "root123456!";
//        Properties connectionProperties = new Properties();
//        connectionProperties.setProperty("user", user);
//        connectionProperties.setProperty("password", password);
//
//        // 写入MySQL表
//        dataset.write().mode("append").jdbc(url, "dm_booking_tickets_summary", connectionProperties);

        // 关闭Spark连接
        spark.close();
    }
}

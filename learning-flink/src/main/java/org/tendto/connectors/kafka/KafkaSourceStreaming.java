package org.tendto.connectors.kafka;

import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.common.time.Time;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.connector.jdbc.JdbcConnectionOptions;
import org.apache.flink.connector.jdbc.JdbcExecutionOptions;
import org.apache.flink.connector.jdbc.JdbcSink;
import org.apache.flink.connector.kafka.source.KafkaSource;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;


public class KafkaSourceStreaming {


    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        // source 1
        KafkaSource<String> source = KafkaSourceUtils.buildKafkaSource();
        DataStreamSource<String> lines = env.fromSource(source, WatermarkStrategy.noWatermarks(), "Kafka Source");


        // source 2
//        FlinkKafkaConsumer<String> kafkaSource = KafkaSourceUtils.buildKafkaConsumer();
//        DataStreamSource<String> lines = env.addSource(kafkaSource);

        //3: 转换
        final SingleOutputStreamOperator<Tuple2<String, Long>> streamOperator = lines.flatMap(
                (String line, Collector<Tuple2<String, Long>> out) -> {
                    String[] words = line.split(" ");
                    for (String word : words) {
                        out.collect(Tuple2.of(word, 1L));
                    }
                }).returns(Types.TUPLE(Types.STRING, Types.LONG));

        // 4: 聚合
        final KeyedStream<Tuple2<String, Long>, String> key = streamOperator.keyBy(e -> e.f0);

        key.sum(1).addSink(JdbcSink.sink(
                "replace into kafka_word_count values (?, ?)",
                (statement, v) -> {
                    statement.setString(1, v.f0);
                    statement.setLong(2, v.f1);
                    System.out.println(v.f0 + " - " + v.f1);
                },
                JdbcExecutionOptions.builder()
                        .withBatchSize(10)   // 这里设置为1 可以实时写到mysql，但是设置为其它数字感觉不太对
                        .withBatchIntervalMs(0)
                        .withMaxRetries(1)
                        .build(),
                new JdbcConnectionOptions.JdbcConnectionOptionsBuilder()
                        .withUrl("jdbc:mysql://192.168.22.107:3306/test_bs?serverTimezone=Asia/Shanghai&characterEncoding=utf8")
                        .withDriverName("com.mysql.cj.jdbc.Driver")
                        .withUsername("root")
                        .withPassword("root123456!")
                        .build()
        ));


        //5.execute
        env.execute();

    }
}

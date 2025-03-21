package org.tendto.operator;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;

public class FlatMapOperator {

    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        DataStream<String> source = env.fromElements("Flink,Spark,Storm,Hadoop");

        /*
            通过 Collector 将映射的 0 到 多个 数据 放到 DataStream 中
         */
        DataStream<String> flat = source.flatMap(new FlatMapFunction<String, String>() {
            @Override
            public void flatMap(String s, Collector<String> collector) {
                String[] str = s.split(",");
                for (String each : str) {
                    collector.collect(each);
                }
            }
        });

        flat.print();
        env.execute("oo");
    }
}

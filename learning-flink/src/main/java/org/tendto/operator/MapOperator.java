package org.tendto.operator;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

public class MapOperator {

    public static void main(String[] args) throws Exception {

        // 1: 获取环境
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        env.setParallelism(2);

        // 2: source
        DataStream<String> line = env.readTextFile("input/word.txt");

        final SingleOutputStreamOperator<String> map = line.map((MapFunction<String, String>) s -> {
            String[] split = s.split(" ");
            return String.join(",", split);
        });

        map.print();

        env.execute();

        env.executeAsync();
    }
}

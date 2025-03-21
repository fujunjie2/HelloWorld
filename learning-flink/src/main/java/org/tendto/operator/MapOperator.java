package org.tendto.operator;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

public class MapOperator {

    public static void main(String[] args) throws Exception {

        // 1: 获取环境
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1);
        pow(env);

    }

    public static void mapToEntity(StreamExecutionEnvironment env) throws Exception {
        // 2: source
        DataStream<String> line = env.readTextFile("input/word.txt");
        final DataStream<SimpleSkill> map = line.map((MapFunction<String, SimpleSkill>) SimpleSkill::new);
        // print 调用的是 对应返回类型的 toString() 方法
        map.print();
        env.execute();
    }

    public static void pow(StreamExecutionEnvironment env) throws Exception {
        DataStream<Integer> dataStream = env.fromElements(1,2,3,4,5,6,7,8,9,10);

        DataStream<Integer> powMap = dataStream.map((MapFunction<Integer, Integer>) s -> s * 2);

        powMap.print();
        env.execute("pow");
    }

}

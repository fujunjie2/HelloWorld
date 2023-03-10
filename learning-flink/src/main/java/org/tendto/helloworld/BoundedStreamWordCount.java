package org.tendto.helloworld;

import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;

public class BoundedStreamWordCount {

    public static void main(String[] args) throws Exception {


        //1: 获取 流式 执行 环境
        StreamExecutionEnvironment streamEnv = StreamExecutionEnvironment.getExecutionEnvironment();

        // 设置当前 job 的并行度
        streamEnv.setParallelism(1);

        //2: 读取文件
        final DataStreamSource<String> source = streamEnv.readTextFile("input/word.txt");

        //3: 转换
        final SingleOutputStreamOperator<Tuple2<String, Long>> streamOperator = source.flatMap(
                (String line, Collector<Tuple2<String, Long>> out) -> {
            String[] words = line.split(" ");
            for (String word : words) {
                out.collect(Tuple2.of(word, 1L));
            }
        }).returns(Types.TUPLE(Types.STRING, Types.LONG));

        // 4: 聚合
        final KeyedStream<Tuple2<String, Long>, String> key = streamOperator.keyBy(e -> e.f0);

        final SingleOutputStreamOperator<Tuple2<String, Long>> sum = key.sum(1);

        sum.print();


        // 流式数据，需要手动触发任务执行
        /*
            10> (practice,1)
            5> (hello,1)
            5> (python,1)
            13> (flink,1)
            15> (netty,1)
            5> (hello,2)
            5> (hello,3)
            15> (netty,2)
         */
        // 编号 表明 由不同的子任务（子线程）执行，编号的多少 取决于并行度
        // 并行度没设置，默认是 当前CPU 核心数
        // 本地执行时，多线程模拟
        // 如果是有界数据 execute() 执行后程序结束退出，如果是 无界数据则
        streamEnv.execute();
    }
}

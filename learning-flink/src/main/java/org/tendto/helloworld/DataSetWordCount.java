package org.tendto.helloworld;

import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.operators.AggregateOperator;
import org.apache.flink.api.java.operators.DataSource;
import org.apache.flink.api.java.operators.FlatMapOperator;
import org.apache.flink.api.java.operators.UnsortedGrouping;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.util.Collector;


/**
 * 这个 word count 使用的是 dataset api, 做批处理的。
 * 官方 推荐flink 1.12 以后  统一使用 dataStream api
 */
public class DataSetWordCount {

    public static void main(String[] args) throws Exception {

        // 1: 创建执行环境 这个执行环境获取的 DataSource 继承自 DataSet，所以这个执行环境只能用于 批处理
        ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();

        // 2: 读取数据
        final DataSource<String> lineWords = env.readTextFile("input/word.txt");


        final FlatMapOperator<String, Tuple2<String, Long>> flatMapOperator = lineWords.flatMap((String line, Collector<Tuple2<String, Long>> out) -> {
            String[] words = line.split(" ");
            for (String word : words) {
                out.collect(Tuple2.of(word, 1L));
            }
        }).returns(Types.TUPLE(Types.STRING, Types.LONG));

        // 这里的 0 是指 Tuple2 中的 元素的位置，Tuple2 有两个元素，对应的元素的索引位置为 0, 1
        // 这里的意思是 根据第一个元素分组
        final UnsortedGrouping<Tuple2<String, Long>> tuple2UnsortedGrouping = flatMapOperator.groupBy(0);

        // 这里的意思是根据Tuple2 的第二个元素累加
        final AggregateOperator<Tuple2<String, Long>> sum = tuple2UnsortedGrouping.sum(1);

        sum.print();
    }
}

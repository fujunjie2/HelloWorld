package org.tendto.helloworld;

import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;

public class StreamWordCount {

    public static void main(String[] args) throws Exception {


        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();


        DataStreamSource<String> lines = env.socketTextStream("192.168.56.131", 7777);


        final SingleOutputStreamOperator<Tuple2<String, Long>> streamOperator =
                lines.flatMap((String line, Collector<Tuple2<String, Long>> out) -> {
            String[] words = line.split(" ");
            for (String word : words) {
                out.collect(Tuple2.of(word, 1L));
            }
        }).returns(Types.TUPLE(Types.STRING, Types.LONG));

        final KeyedStream<Tuple2<String, Long>, String> key = streamOperator.keyBy(e -> e.f0);

        final SingleOutputStreamOperator<Tuple2<String, Long>> sum = key.sum(1);

        sum.print();

        env.execute();
    }
}

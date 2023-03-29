package org.tendto.connectors.kafka;

import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.sink.RichSinkFunction;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class MysqlSink extends RichSinkFunction<Tuple2<String, Long>> {

    private PreparedStatement ps;
    private Connection connection;

    private static final String UPSERT = "replace into kafka_word_count values (?, ?)";

    @Override
    public void open(Configuration parameters) throws Exception {
        super.open(parameters);


    }

    @Override
    public void invoke(Tuple2<String, Long> value, Context context) throws Exception {
        String field = value.f0;
        Long count = value.f1;


    }



    @Override
    public void close() throws Exception {
        super.close();
        if (connection != null) {
            connection.close();
        }
        if (ps != null) {
            ps.close();
        }
    }
}

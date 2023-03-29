package org.tendto.connectors.kafka;

import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.connector.kafka.source.KafkaSource;
import org.apache.flink.connector.kafka.source.enumerator.initializer.OffsetsInitializer;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;

import java.util.Properties;

/**
 * https://nightlies.apache.org/flink/flink-docs-release-1.13/docs/connectors/datastream/overview/
 *
 * 不通版本的 flink 其 connector 的api有点差异
 */

public class KafkaSourceUtils {

    private static final String BROKERS = "192.168.56.111:9092";
    private static final String TOPICS = "quickstart-events";
    private static final String GROUP_ID = "flink";

    public static KafkaSource<String> buildKafkaSource() {
        KafkaSource<String> source = KafkaSource.<String>builder()
                .setBootstrapServers(BROKERS)
                .setTopics(TOPICS)
                .setGroupId(GROUP_ID)
                .setStartingOffsets(OffsetsInitializer.latest())
                .setValueOnlyDeserializer(new SimpleStringSchema())
                .build();
        return source;
    }

    public static FlinkKafkaConsumer<String> buildKafkaConsumer() {
        Properties props  = new Properties();
        props.setProperty("bootstrap.servers", BROKERS);
        props.setProperty("group.id", GROUP_ID);
        props.setProperty("auto.offset.reset","latest");
        props.setProperty("flink.partition-discovery.interval-millis","5000");//会开启一个后台线程每隔5s检测一下Kafka的分区情况
        props.setProperty("enable.auto.commit", "true");
        props.setProperty("auto.commit.interval.ms", "2000");

        FlinkKafkaConsumer<String> kafkaSource = new FlinkKafkaConsumer<>(
                BROKERS,
                new SimpleStringSchema(),
                props);
        kafkaSource.setStartFromGroupOffsets();
        return kafkaSource;
    }
}

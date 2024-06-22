package srimalar.sample.kafka.dev;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class SampleProducer {
    private static final Logger log = LoggerFactory.getLogger(SampleProducer.class);

    public static void main(String[] args) {
        log.info("I am a Kafka Producer");

        Properties prop = KafkaConfig.getConfig();
        prop.put("key.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");

        prop.put("value.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");

        KafkaProducer<String, String> producer = new KafkaProducer<>(prop);

        // create a producer record
        ProducerRecord<String, String> producerRecord =
                new ProducerRecord<>("sample_topic", "asdjfhhegfdfasdfasdfasf", "Hi, This is Ramesh Rajeswari");

        // send data - asynchronous
        producer.send(producerRecord);

        // flush data - synchronous
        producer.flush();
        // flush and close producer
        producer.close();
    }
}

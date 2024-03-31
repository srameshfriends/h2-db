package srimalar.examples.kafka.dev;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.PartitionInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class KafkaTopic {

    public static void createTopic() {
        AdminClient adminClient = AdminClient.create(KafkaConfig.getConfig());
        NewTopic newTopic = new NewTopic("sample_topic", 1, (short)1);
        List<NewTopic> newTopics = new ArrayList<>();
        newTopics.add(newTopic);
        adminClient.createTopics(newTopics);
        adminClient.close();
    }

    public static void listTopic() {
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(KafkaConfig.getConfig());
        Map<String, List<PartitionInfo>> topics = consumer.listTopics();
        topics.entrySet().forEach(new Consumer<Map.Entry<String, List<PartitionInfo>>>() {
            @Override
            public void accept(Map.Entry<String, List<PartitionInfo>> stringListEntry) {
                System.out.println("------------------------- " + stringListEntry.getKey() +  " ---------------------------------");
                stringListEntry.getValue().forEach(new Consumer<PartitionInfo>() {
                    @Override
                    public void accept(PartitionInfo partitionInfo) {
                        System.out.println(partitionInfo.topic());
                    }
                });
            }
        });
        consumer.close();
    }

    public static void main(String[] args) {
        listTopic();
        // createTopic();
    }
}

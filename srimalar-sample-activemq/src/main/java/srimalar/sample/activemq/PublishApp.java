package srimalar.sample.activemq;

import org.fusesource.mqtt.client.BlockingConnection;
import org.fusesource.mqtt.client.MQTT;
import org.fusesource.mqtt.client.QoS;
import org.fusesource.mqtt.client.Topic;

public class PublishApp {
    public static void main(String[] args) throws Exception {
        MQTT mqtt = new MQTT();
        mqtt.setHost("tcp://localhost:61616");
        BlockingConnection connection = mqtt.blockingConnection();
        connection.connect();

        String payload1 = "This is message 1";
        String payload2 = "This is message 2";
        String payload3 = "This is message 3";

        connection.publish("mqtt/example/publish", payload1.getBytes(), QoS.AT_LEAST_ONCE, false);
        connection.publish("mqtt/test", payload2.getBytes(), QoS.AT_MOST_ONCE, false);
        connection.publish("foo/1/bar", payload3.getBytes(), QoS.AT_MOST_ONCE, false);

        // SpringApplication.run(SampleActivemqApplication.class, args);
    }
}

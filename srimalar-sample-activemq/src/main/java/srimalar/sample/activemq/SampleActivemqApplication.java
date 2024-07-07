package srimalar.sample.activemq;

import org.fusesource.mqtt.client.BlockingConnection;
import org.fusesource.mqtt.client.MQTT;
import org.fusesource.mqtt.client.QoS;
import org.fusesource.mqtt.client.Topic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

/*@SpringBootApplication*/
public class SampleActivemqApplication {

	public static void main(String[] args) throws Exception {
        MQTT mqtt = new MQTT();
        mqtt.setHost("tcp://localhost:61616");
        BlockingConnection connection = mqtt.blockingConnection();
        connection.connect();

        Topic[] topics = { new Topic("mqtt/example/publish", QoS.AT_LEAST_ONCE), new Topic("test/#", QoS.EXACTLY_ONCE), new Topic("foo/+/bar", QoS.AT_LEAST_ONCE) };
        connection.subscribe(topics);

		// SpringApplication.run(SampleActivemqApplication.class, args);
	}
}

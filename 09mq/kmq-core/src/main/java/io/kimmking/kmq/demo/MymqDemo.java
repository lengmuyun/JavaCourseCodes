package io.kimmking.kmq.demo;

import io.kimmking.kmq.core.broker.Broker;
import io.kimmking.kmq.core.broker.MyBroker;
import io.kimmking.kmq.core.consumer.KmqConsumer;
import io.kimmking.kmq.core.message.KmqMessage;
import io.kimmking.kmq.core.producer.KmqProducer;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MymqDemo {

    public static void main(String[] args) {
        String topic = "my.test";
        Broker broker = new MyBroker();
        broker.createTopic("my.test");

        KmqProducer producer = broker.createProducer();
        for (int i = 0; i < 10; i++) {
            Order order = new Order(1000L + i, System.currentTimeMillis(), "USD2CNY", 6.51d);
            producer.send(topic, new KmqMessage(null, order));
        }

        consumerWithAck(topic, broker, "default_consumer_name");
//        consumerWithNoAck(topic, broker, "consumer-01");
    }

    private static void consumerWithAck(String topic, Broker broker, String consumerName) {
        KmqConsumer consumer = broker.createConsumer(consumerName);
        consumer.subscribe(topic);

        while (consumer.poll() != null) {
            KmqMessage message = consumer.poll();
            log.info("message from mq: " + message);
            consumer.acknowledge(message);
        }
        log.info(consumerName + "消费结束");
    }

    private static void consumerWithNoAck(String topic, Broker broker, String consumerName) {
        KmqConsumer consumer = broker.createConsumer(consumerName);
        consumer.subscribe(topic);

        // 不ack一直消费第一条数据
        while (consumer.poll() != null) {
            KmqMessage message = consumer.poll();
            log.info("message from mq: " + message);
        }
        log.info(consumerName + "消费结束");
    }

}

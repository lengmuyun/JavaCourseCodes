package io.kimmking.kmq.core.broker;

import io.kimmking.kmq.core.consumer.KmqConsumer;
import io.kimmking.kmq.core.mq.MessageQueue;
import io.kimmking.kmq.core.producer.KmqProducer;

public interface Broker {

    /** 创建topic */
    void createTopic(String name);

    /** 根据topic名称查找mq */
    MessageQueue findKmq(String topic);

    /** 创建生产者 */
    KmqProducer createProducer();

    /** 创建命名消费者 */
    KmqConsumer createConsumer(String consumerName);

}

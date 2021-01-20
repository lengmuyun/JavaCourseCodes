package io.kimmking.kmq.core.broker;

import io.kimmking.kmq.core.consumer.KmqConsumer;
import io.kimmking.kmq.core.mq.MessageQueue;
import io.kimmking.kmq.core.producer.KmqProducer;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class AbstractBroker implements Broker {

    public static final int CAPACITY = 10000;
    protected final Map<String, MessageQueue> messageQueueMap = new ConcurrentHashMap<>(64);

    @Override
    public MessageQueue findKmq(String topic) {
        return this.messageQueueMap.get(topic);
    }

    @Override
    public KmqProducer createProducer() {
        return new KmqProducer(this);
    }

    @Override
    public KmqConsumer createConsumer(String consumerName) {
        return new KmqConsumer(this, consumerName);
    }


}

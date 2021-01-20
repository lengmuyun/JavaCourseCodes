package io.kimmking.kmq.core.producer;

import io.kimmking.kmq.core.broker.Broker;
import io.kimmking.kmq.core.message.KmqMessage;
import io.kimmking.kmq.core.mq.MessageQueue;

public class KmqProducer {

    private Broker broker;

    public KmqProducer(Broker broker) {
        this.broker = broker;
    }

    public boolean send(String topic, KmqMessage message) {
        MessageQueue messageQueue = this.broker.findKmq(topic);
        if (null == messageQueue) throw new RuntimeException("Topic[" + topic + "] doesn't exist.");
        return messageQueue.send(message);
    }
}

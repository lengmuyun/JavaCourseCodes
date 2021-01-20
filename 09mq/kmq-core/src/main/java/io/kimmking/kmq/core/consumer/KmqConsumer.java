package io.kimmking.kmq.core.consumer;

import io.kimmking.kmq.core.broker.Broker;
import io.kimmking.kmq.core.message.KmqMessage;
import io.kimmking.kmq.core.mq.MessageQueue;

import java.util.concurrent.TimeUnit;

public class KmqConsumer<T> implements MessageQueueConsumer<T> {

    private final Broker broker;
    private String consumerName;
    private MessageQueue messageQueue;

    public KmqConsumer(Broker broker, String consumerName) {
        this.broker = broker;
        this.consumerName = consumerName;
    }

    public void subscribe(String topic) {
        this.messageQueue = this.broker.findKmq(topic);
        if (null == messageQueue) throw new RuntimeException("Topic[" + topic + "] doesn't exist.");
    }

    public KmqMessage<T> poll(long timeout) {
        return messageQueue.poll(consumerName, timeout, TimeUnit.SECONDS);
    }

    @Override
    public KmqMessage<T> poll() {
        return messageQueue.poll(consumerName);
    }

    @Override
    public boolean acknowledge(KmqMessage<T> message) {
        return messageQueue.acknowledge(consumerName, message);
    }

}

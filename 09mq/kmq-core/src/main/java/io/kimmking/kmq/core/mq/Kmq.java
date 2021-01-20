package io.kimmking.kmq.core.mq;

import io.kimmking.kmq.core.message.KmqMessage;
import lombok.SneakyThrows;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public final class Kmq implements MessageQueue {

    public Kmq(String topic, int capacity) {
        this.topic = topic;
        this.capacity = capacity;
        this.queue = new LinkedBlockingQueue(capacity);
    }

    private String topic;

    private int capacity;

    private LinkedBlockingQueue<KmqMessage> queue;

    public boolean send(KmqMessage message) {
        return queue.offer(message);
    }

    public KmqMessage poll(String consumerName) {
        return queue.poll();
    }

    @SneakyThrows
    @Override
    public KmqMessage poll(String consumerName, long timeout, TimeUnit timeUnit) {
        return queue.poll(timeout, timeUnit);
    }

    @Override
    public boolean acknowledge(String consumerName, KmqMessage message) {
        // 不支持ack
        throw new UnsupportedOperationException();
    }

    @Override
    public Integer getOffset(String consumerName) {
        // 不支持命名消费者
        throw new UnsupportedOperationException();
    }

}

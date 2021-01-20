package io.kimmking.kmq.core.mq;

import io.kimmking.kmq.core.message.KmqMessage;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 基于数组的消息队列
 * 支持ack
 */
@Slf4j
public class ArrayMessageQueue implements MessageQueue {

    private final String topic;
    private final int capacity;
    /** 记录消息数组写的位置 */
    private int writePostiton;
    private KmqMessage[] queue;

    /** 记录命名消费者的消费位置 */
    private Map<String, Integer> positionMap = new HashMap<>();

    public ArrayMessageQueue(String topic, int capacity) {
        this.topic = topic;
        this.capacity = capacity;
        this.queue = new KmqMessage[capacity];
        this.writePostiton = 0;
    }

    @Override
    public boolean send(KmqMessage message) {
        if (writePostiton >= capacity) {
            log.info("MessageQueue is Full!");
            return false;
        }
        message.setPosition(writePostiton);
        this.queue[writePostiton++] = message;
        return true;
    }

    @Override
    public KmqMessage poll(String consumerName) {
        Integer position = getOffset(consumerName);
        if (position >= capacity) return null;

        return queue[position];
    }

    @Override
    public KmqMessage poll(String consumerName, long timeout, TimeUnit timeUnit) {
        // 不支持阻塞式拉消息
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean acknowledge(String consumerName, KmqMessage message) {
        Integer position = message.getPosition();
        if (position == null) {
            throw new UnsupportedOperationException();
        }
        positionMap.put(consumerName, position + 1);
        return true;
    }

    @Override
    public Integer getOffset(String consumerName) {
        return positionMap.getOrDefault(consumerName, 0);
    }

}

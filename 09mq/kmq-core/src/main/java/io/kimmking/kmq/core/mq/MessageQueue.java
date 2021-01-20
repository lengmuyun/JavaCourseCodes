package io.kimmking.kmq.core.mq;

import io.kimmking.kmq.core.message.KmqMessage;

import java.util.concurrent.TimeUnit;

public interface MessageQueue {

    /** 往MQ中发送消息 */
    boolean send(KmqMessage message);

    /** 从MQ中拉消息 */
    KmqMessage poll(String consumerName);

    /** 阻塞式从MQ中拉消息 */
    KmqMessage poll(String consumerName, long timeout, TimeUnit timeUnit);

    /** ack确认 */
    boolean acknowledge(String consumerName, KmqMessage message);

    /** 获取命名消费者的读取位置 */
    Integer getOffset(String consumerName);

}

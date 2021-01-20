package io.kimmking.kmq.core.consumer;

import io.kimmking.kmq.core.message.KmqMessage;

public interface MessageQueueConsumer<T> {

    void subscribe(String topic);

    KmqMessage<T> poll();

    boolean acknowledge(KmqMessage<T> message);

}

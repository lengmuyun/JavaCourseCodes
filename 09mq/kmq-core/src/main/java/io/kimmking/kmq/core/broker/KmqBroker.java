package io.kimmking.kmq.core.broker;

import io.kimmking.kmq.core.mq.Kmq;

public final class KmqBroker extends AbstractBroker { // Broker+Connection

    @Override
    public void createTopic(String name) {
        messageQueueMap.putIfAbsent(name, new Kmq(name,CAPACITY));
    }

}

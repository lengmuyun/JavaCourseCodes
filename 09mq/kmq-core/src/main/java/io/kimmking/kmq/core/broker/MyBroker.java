package io.kimmking.kmq.core.broker;

import io.kimmking.kmq.core.mq.ArrayMessageQueue;

public class MyBroker extends AbstractBroker {

    @Override
    public void createTopic(String name) {
        messageQueueMap.putIfAbsent(name, new ArrayMessageQueue(name, CAPACITY));
    }

}

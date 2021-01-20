package io.kimmking.kmq.core.message;

import lombok.Data;

import java.util.HashMap;

@Data
public class KmqMessage<T> {

    private HashMap<String, Object> headers;

    private T body;

    /** 记录消息在消息队列中的位置 */
    private Integer position;

    public KmqMessage(HashMap<String, Object> headers, T body) {
        this.headers = headers;
        this.body = body;
    }

}

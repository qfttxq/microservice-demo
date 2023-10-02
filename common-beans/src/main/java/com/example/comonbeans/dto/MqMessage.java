package com.example.comonbeans.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Map;
@Data
@NoArgsConstructor
public class MqMessage<T> implements Serializable{
    private T body;
    private Map<String,Object> headers;

    public MqMessage(T body) {
       this(body,null);
    }

    public MqMessage(T body, Map<String, Object> headers) {
        this.body = body;
        this.headers = headers;
    }

}

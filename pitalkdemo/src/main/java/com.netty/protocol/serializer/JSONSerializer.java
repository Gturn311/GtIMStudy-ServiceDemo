package com.netty.protocol.serializer;

import com.alibaba.fastjson.JSON;

/**
 * @author Gturn
 * @Title: JSONSerializer
 * @ProjectName Netty—Study
 * @Description: TODO
 * @date 2019/9/25 16:55
 */

public class JSONSerializer implements Serializer {


    public static JSONSerializer getJSONSerializer(){
        return new JSONSerializer();
    }
    @Override
    public byte getSerializerAlgorithm() {
        return SerializerAlgorithm.JSON.getCode();
    }

    @Override
    public byte[] serialize(Object object) {
        return JSON.toJSONBytes(object);
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) {
        return JSON.parseObject(bytes, clazz);
    }
}

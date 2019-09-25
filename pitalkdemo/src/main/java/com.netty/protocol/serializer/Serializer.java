package com.netty.protocol.serializer;

/**
 * @author Gturn
 * @Title: Serializer
 * @ProjectName Netty—Study
 * @Description: TODO
 * @date 2019/9/25 16:53
 */

public interface Serializer {
    /**
     * json 序列化
     */
    byte JSON_SERIALIZER = SerializerAlgorithm.JSON.getCode();

    Serializer DEFAULT = JSONSerializer.getJSONSerializer();

    /**
     * 序列化算法
     */
    byte getSerializerAlgorithm();

    /**
     * java 对象转换成二进制
     */
    byte[] serialize(Object object);

    /**
     * 二进制转换成 java 对象
     */
    <T> T deserialize(Class<T> clazz, byte[] bytes);
}

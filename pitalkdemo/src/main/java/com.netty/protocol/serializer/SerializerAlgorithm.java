package com.netty.protocol.serializer;


import java.util.ArrayList;
import java.util.List;

public enum SerializerAlgorithm {
    JSON(Byte.valueOf("1"), JSONSerializer.getJSONSerializer());

    private Byte code;
    private Serializer serializer;

    private SerializerAlgorithm(Byte code, Serializer serializer) {
        setCode(code);
        setSerializer(serializer);
    }

    public Byte getCode() {
        return code;
    }

    public void setCode(Byte code) {
        this.code = code;
    }

    public Serializer getSerializer() {
        return serializer;
    }

    public void setSerializer(Serializer serializer) {
        this.serializer = serializer;
    }

    public static SerializerAlgorithm findByCode(Byte code) {
        for (SerializerAlgorithm command : SerializerAlgorithm.values()) {
            if (command.getCode().equals(code)) {
                return command;
            }
        }
        throw new IllegalArgumentException("不存在该指令：" + code);
    }

    public static List<Byte> getAllSerializerAlgorithms() {
        ArrayList<Byte> results = new ArrayList<>();
        for (SerializerAlgorithm command : SerializerAlgorithm.values()) {
            results.add(command.getCode());
        }
        return results;
    }
}

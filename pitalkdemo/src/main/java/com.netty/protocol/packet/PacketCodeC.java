package com.netty.protocol.packet;

import com.netty.protocol.serializer.Serializer;
import com.netty.protocol.serializer.SerializerAlgorithm;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

/**
 * @author Gturn
 * @Title: PacketCodeC
 * @ProjectName pitalkdemo
 * @Description: TODO
 * @date 2019/9/25 17:09
 */

public class PacketCodeC {
    private static final int MAGIC_NUMBER = 0x12345678;

    public ByteBuf encode(Packet packet) {
        // 1. 创建 ByteBuf 对象
        ByteBuf byteBuf = ByteBufAllocator.DEFAULT.ioBuffer();
        // 2. 序列化 Java 对象
        byte[] bytes = Serializer.DEFAULT.serialize(packet);

        // 3. 实际编码过程
        byteBuf.writeInt(MAGIC_NUMBER);
        byteBuf.writeByte(packet.getVersion());
        byteBuf.writeByte(Serializer.DEFAULT.getSerializerAlgorithm());
        byteBuf.writeByte(packet.getCommand());
        byteBuf.writeInt(bytes.length);
        byteBuf.writeBytes(bytes);

        return byteBuf;
    }

    public Packet decode(ByteBuf byteBuf) {
        // 跳过 magic number
        byteBuf.skipBytes(4);

        // 跳过版本号
        byteBuf.skipBytes(1);

        // 序列化算法
        byte serializeAlgorithm = byteBuf.readByte();

        // 指令
        byte command = byteBuf.readByte();

        // 数据包长度
        int length = byteBuf.readInt();

        byte[] bytes = new byte[length];
        byteBuf.readBytes(bytes);

        Class<? extends Packet> requestType = getRequestType(command);
        Serializer serializer = getSerializer(serializeAlgorithm);

        if (requestType != null && serializer != null) {
            return serializer.deserialize(requestType, bytes);
        }

        return null;
    }

    private Serializer getSerializer(byte serializeAlgorithm) {

        return SerializerAlgorithm.findByCode(serializeAlgorithm).getSerializer();
    }

    private Class<? extends Packet> getRequestType(byte command) {

        return Command.findByCode(command).getPacket().getClass();
    }
}

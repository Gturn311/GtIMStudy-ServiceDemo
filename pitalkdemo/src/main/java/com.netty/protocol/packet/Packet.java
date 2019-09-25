package com.netty.protocol.packet;

/**
 * @author Gturn
 * @Title: Packet
 * @ProjectName Netty—Study
 * @Description: TODO
 * @date 2019/9/25 16:37
 */

public abstract class Packet {
    /**
     * 协议版本
     */
    private Byte version = 1;

    /**
     * 指令
     */
    public abstract Byte getCommand();

    public Byte getVersion() {
        return version;
    }

    public void setVersion(Byte version) {
        this.version = version;
    }


}

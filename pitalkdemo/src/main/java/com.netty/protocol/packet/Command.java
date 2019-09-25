package com.netty.protocol.packet;

import java.util.ArrayList;
import java.util.List;

public enum Command {

    LOGIN_REQUEST (Byte.valueOf("1"), LoginRequestPacket.getPacket());

    private Byte  code;
    private Packet packet;

    private Command(Byte code, Packet packet) {
        setCode(code);
        setPacket(packet);
    }

    public Byte getCode() {
        return code;
    }

    public void setCode(Byte code) {
        this.code = code;
    }

    public Packet getPacket() {
        return packet;
    }

    public void setPacket(Packet packet) {
        this.packet = packet;
    }

    public static Command findByCode(Byte code) {
        for (Command command : Command.values()) {
            if (command.getCode().equals(code)) {
                return command;
            }
        }
        throw new IllegalArgumentException("不存在该指令：" + code);
    }

    public static List<Byte> getAllCommands(){
        ArrayList<Byte> results = new ArrayList<>();
        for (Command command : Command.values()) {
            results.add(command.getCode());
        }
        return results;
    }
}

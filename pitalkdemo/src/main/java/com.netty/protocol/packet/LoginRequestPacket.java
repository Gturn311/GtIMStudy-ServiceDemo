package com.netty.protocol.packet;

/**
 * @author Gturn
 * @Title: LoginRequestPacket
 * @ProjectName Netty—Study
 * @Description: TODO
 * @date 2019/9/25 16:48
 */

public class LoginRequestPacket extends Packet {

    private Integer userId;

    private String username;

    private String password;


    @Override
    public Byte getCommand() {
        return Command.LOGIN_REQUEST.getCode();
    }

    public static LoginRequestPacket getPacket() {
        return new LoginRequestPacket();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

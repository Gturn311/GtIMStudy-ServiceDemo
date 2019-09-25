import com.netty.protocol.packet.LoginRequestPacket;
import com.netty.protocol.packet.Packet;
import com.netty.protocol.packet.PacketCodeC;
import com.netty.protocol.serializer.JSONSerializer;
import com.netty.protocol.serializer.Serializer;
import io.netty.buffer.ByteBuf;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Gturn
 * @Title: PackET
 * @ProjectName pitalkdemo
 * @Description: TODO
 * @date 2019/9/25 17:13
 */

public class PacketCodeCTest {
    @Test
    public void encode() {

        Serializer serializer = JSONSerializer.getJSONSerializer ();
        LoginRequestPacket loginRequestPacket = LoginRequestPacket.getPacket();

        loginRequestPacket.setVersion(((byte) 1));
        loginRequestPacket.setUserId(123);
        loginRequestPacket.setUsername("zhangsan");
        loginRequestPacket.setPassword("password");

        PacketCodeC packetCodeC = new PacketCodeC();
        ByteBuf byteBuf = packetCodeC.encode(loginRequestPacket);
        Packet decodedPacket = packetCodeC.decode(byteBuf);

        Assert.assertArrayEquals(serializer.serialize(loginRequestPacket), serializer.serialize(decodedPacket));

    }
}

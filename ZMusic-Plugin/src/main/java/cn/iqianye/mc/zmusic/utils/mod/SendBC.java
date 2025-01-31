package cn.iqianye.mc.zmusic.utils.mod;

import cn.iqianye.mc.zmusic.ZMusic;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.nio.charset.StandardCharsets;

public class SendBC implements Send {

    @Override
    public void sendAM(Object playerObj, String data) {
        ProxiedPlayer player = (ProxiedPlayer) playerObj;
        if (player == null)
            return;
        try {
            byte[] bytes = data.getBytes(StandardCharsets.UTF_8);
            ByteBuf buf = Unpooled.buffer(bytes.length + 1);
            buf.writeByte(666);
            buf.writeBytes(bytes);
            ZMusic.runTask.runAsync(() -> player.sendData("allmusic:channel", buf.array()));
        } catch (
                Exception e) {
            ZMusic.log.sendDebugMessage("[Mod通信] 数据发送发生错误");
        }
    }

    @Override
    public void sendABF(Object playerObj, String data) {
        ProxiedPlayer player = (ProxiedPlayer) playerObj;
        if (player == null)
            return;
        try {
            ZMusic.runTask.runAsync(() -> player.sendData("AudioBuffer", data.getBytes()));
        } catch (
                Exception e) {
            ZMusic.log.sendDebugMessage("[Mod通信] 数据发送发生错误");
        }
    }
}

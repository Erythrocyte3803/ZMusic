package me.zhenxin.zmusic

import org.slf4j.LoggerFactory
import me.zhenxin.zmusic.event.EventHandler
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
import net.minecraft.network.PacketByteBuf
import net.minecraft.util.Identifier
import java.nio.charset.StandardCharsets


/**
 * Mod 主入口
 *
 * @author 真心
 * @since 2021/6/10 13:45
 * @email qgzhenxin@qq.com
 */
class ZMusic : ModInitializer {
    private val logger = LoggerFactory.getLogger(ZMusic::class.java)
    override fun onInitialize() {
        logger.info("ZMusic Loading...")
        logger.info("Register Packet Receiver...")
        val channelAll = Identifier("allmusic", "channel")
        val channelZM = Identifier("zmusic", "channel")
        ClientPlayNetworking.registerGlobalReceiver(channelAll) { _, _, buffer, _ ->
            getBuffer(buffer)
        }
        ClientPlayNetworking.registerGlobalReceiver(channelZM) { _, _, buffer, _ ->
            getBuffer(buffer)
        }
        logger.info("ZMusic Loaded!")
    }

    private fun getBuffer(buffer: PacketByteBuf) {
        val buf = ByteArray(buffer.readableBytes())
        buffer.readBytes(buf)
        buf[0] = 0
        val data = String(buf, StandardCharsets.UTF_8).substring(1)
        EventHandler.onPacketMessage(data)
    }
}

package me.zhenxin.zmusic.event

import me.zhenxin.zmusic.utils.getRealUrl
import net.minecraft.client.MinecraftClient
import net.minecraft.sound.SoundCategory
import java.net.URL
import kotlin.concurrent.thread

/**
 * 事件监听
 *
 * @author 真心
 * @since 2021/6/13 13:23
 * @email qgzhenxin@qq.com
 */
object EventHandler {
    /**
     * 分析插件发送的消息
     * @param message 消息内容
     */
    fun onPacketMessage(message: String) {
        thread {
            try {
                when {
                    message == "[Stop]" -> {
                        // 停止播放
                    }
                    message.startsWith("[Play]") -> {
                        // 停止音乐和唱片机播放
                        MinecraftClient.getInstance().soundManager.stopSounds(null, SoundCategory.MUSIC)
                        MinecraftClient.getInstance().soundManager.stopSounds(null, SoundCategory.RECORDS)
                        // 获取URL
                        var url = URL(message.replace("[Play]", ""))
                        // 获取真实链接
                        url = url.getRealUrl()
                        // 播放音乐
                    }
                    message.startsWith("[Lyric]") -> {
                        // 歌词
                    }
                    message.startsWith("[Info]") -> {
                        // Hud信息
                    }
                    message.startsWith("[List]") -> {
                        // 播放列表
                    }
                    message.startsWith("[Img]") -> {
                        // 专辑图片
                    }
                    message == "[ClearHud]" -> {
                        // 清空Hud
                    }
                    message.startsWith("{") -> {
                        // JSON信息
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun onServerQuit() {

    }
}

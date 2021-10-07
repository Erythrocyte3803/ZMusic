package me.zhenxin.zmusic.utils

import java.net.HttpURLConnection
import java.net.URL

/**
 * URL 扩展函数
 *
 * @author 真心
 * @since 2021/6/12 22:43
 * @email qgzhenxin@qq.com
 */
/**
 * 获取网易云真实链接
 */
fun URL.getRealUrl(): URL {
    if (this.toString().contains("music.163.com/song/media/outer/url?id=")) {
        try {
            val con: HttpURLConnection = this.openConnection() as HttpURLConnection
            con.requestMethod = "GET"
            con.connectTimeout = 4 * 1000
            con.setRequestProperty(
                "User-Agent",
                "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/84.0.4147.105 Safari/537.36 Edg/84.0.522.52"
            )
            con.setRequestProperty("Host", "music.163.com")
            con.connect()
            if (con.responseCode == 302) {
                return URL(con.getHeaderField("Location"))
            }
            return con.url
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    return this
}

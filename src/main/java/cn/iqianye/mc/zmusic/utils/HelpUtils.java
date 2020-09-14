package cn.iqianye.mc.zmusic.utils;

import org.bukkit.command.CommandSender;

public class HelpUtils {

    /**
     * 发送帮助
     *
     * @param cmdName 主命令名称
     * @param type    帮助类型
     * @param player  玩家
     */
    public static void sendHelp(String cmdName, String type, CommandSender player) {
        switch (type) {
            case "main":
                MessageUtils.sendNormalMessage("§6========= §r[§bZMusic§r] §d帮助 By 真心 §6=========", player);
                MessageUtils.sendNormalMessage("/" + cmdName + " help - 查看主帮助(当前).", player);
                MessageUtils.sendNormalMessage("/" + cmdName + " help play - 查看播放帮助.", player);
                MessageUtils.sendNormalMessage("/" + cmdName + " help music - 查看点歌帮助.", player);
                MessageUtils.sendNormalMessage("/" + cmdName + " help search - 查看搜索帮助.", player);
                MessageUtils.sendNormalMessage("/" + cmdName + " help playlist - 查看歌单帮助.", player);
                if (player.hasPermission("zmusic.admin") || player.isOp()) {
                    MessageUtils.sendNormalMessage("/" + cmdName + " help admin - 查看管理员帮助.", player);
                }
                MessageUtils.sendNormalMessage("=========================================", player);
                MessageUtils.sendNormalMessage("/" + cmdName + " stop - 停止播放", player);
                MessageUtils.sendNormalMessage("/" + cmdName + " loop - 开/关循环播放", player);
                MessageUtils.sendNormalMessage("/" + cmdName + " url [MP3地址] - 播放网络音乐", player);
                MessageUtils.sendNormalMessage("/" + cmdName + " 163hot [歌名] - 获取网易云音乐热评(前三)", player);
                MessageUtils.sendNormalMessage("§6=========================================", player);
                break;
            case "admin":
                MessageUtils.sendNormalMessage("§6========= §r[§bZMusic§r] §d管理员帮助 By 真心 §6=========", player);
                MessageUtils.sendNormalMessage("/" + cmdName + " playAll [搜索源] [歌名] - 强制为所有玩家播放音乐.", player);
                MessageUtils.sendNormalMessage("/" + cmdName + " stopAll - 强制为所有玩家停止播放音乐.", player);
                MessageUtils.sendNormalMessage("/" + cmdName + " reload - 重载配置文件.", player);
                MessageUtils.sendNormalMessage("§6=========================================", player);
                break;
            case "play":
                MessageUtils.sendNormalMessage("§6========= §r[§bZMusic§r] §d播放帮助 By 真心 §6=========", player);
                MessageUtils.sendNormalMessage("/" + cmdName + " play 163 <歌名> - 网易云音乐播放§a.", player);
                MessageUtils.sendNormalMessage("/" + cmdName + " play qq <歌名> - QQ音乐播放§a.", player);
                MessageUtils.sendNormalMessage("/" + cmdName + " play kugou <歌名> - 酷狗音乐播放§a.", player);
                MessageUtils.sendNormalMessage("/" + cmdName + " play kuwo <歌名> - 酷我音乐播放§a.", player);
                MessageUtils.sendNormalMessage("/" + cmdName + " play bilibili <:auXXXXX/歌名> - 哔哩哔哩音乐播放§a.", player);
                MessageUtils.sendNormalMessage("§6=========================================", player);
                break;
            case "playlist":
                MessageUtils.sendNormalMessage("§6========= §r[§bZMusic§r] §d歌单帮助 By 真心 §6=========", player);
                MessageUtils.sendNormalMessage("/" + cmdName + " playlist [qq/163/netease] import <歌单链接> - 导入歌单§a.", player);
                MessageUtils.sendNormalMessage("/" + cmdName + " playlist [qq/163/netease] list - 查看已导入的歌单列表§a.", player);
                MessageUtils.sendNormalMessage("/" + cmdName + " playlist [qq/163/netease] play <歌单ID> - 播放已导入的歌单§a.", player);
                MessageUtils.sendNormalMessage("/" + cmdName + " playlist [qq/163/netease] update - 更新已导入歌单.", player);
                MessageUtils.sendNormalMessage("/" + cmdName + " playlist type [normal/loop/random] - 设置歌单播放模式§a.", player);
                MessageUtils.sendNormalMessage("/" + cmdName + " playlist prev - 切换到下一首歌曲§a.", player);
                MessageUtils.sendNormalMessage("/" + cmdName + " playlist next - 切换到下一首歌曲§a.", player);
                if (player.hasPermission("zmusic.admin") || player.isOp()) {
                    MessageUtils.sendNormalMessage("§6=========================================", player);
                    MessageUtils.sendNormalMessage("/" + cmdName + " playlist global [qq/163/netease] import <歌单链接> - 导入全服歌单§a.", player);
                    MessageUtils.sendNormalMessage("/" + cmdName + " playlist global [qq/163/netease] list - 查看已导入的全服歌单列表§a.", player);
                    MessageUtils.sendNormalMessage("/" + cmdName + " playlist global [qq/163/netease] play <歌单ID> - 播放已导入的全服歌单§a.", player);
                    MessageUtils.sendNormalMessage("/" + cmdName + " playlist global [qq/163/netease] update - 更新已导入歌单.", player);
                }
                MessageUtils.sendNormalMessage("§6=========================================", player);
                break;
            case "music":
                MessageUtils.sendNormalMessage("§6========= §r[§bZMusic§r] §d点歌帮助 By 真心 §6=========", player);
                MessageUtils.sendNormalMessage("/" + cmdName + " music 163 <歌名> - 网易云音乐点歌§a.", player);
                MessageUtils.sendNormalMessage("/" + cmdName + " music qq <歌名> - QQ音乐点歌§a.", player);
                MessageUtils.sendNormalMessage("/" + cmdName + " music kugou <歌名> - 酷狗音乐点歌§a.", player);
                MessageUtils.sendNormalMessage("/" + cmdName + " music kuwo <歌名> - 酷我音乐点歌§a.", player);
                MessageUtils.sendNormalMessage("/" + cmdName + " music bilibili <:auXXXXX歌名> - 哔哩哔哩音乐点歌§a.", player);
                MessageUtils.sendNormalMessage("§6=========================================", player);
                break;
            case "search":
                MessageUtils.sendNormalMessage("§6========= §r[§bZMusic§r] §d搜索帮助 By 真心 §6=========", player);
                MessageUtils.sendNormalMessage("/" + cmdName + " search 163 <歌名> - 网易云音乐搜索§a.", player);
                MessageUtils.sendNormalMessage("/" + cmdName + " search qq <歌名> - QQ音乐搜索§a.", player);
                MessageUtils.sendNormalMessage("/" + cmdName + " search kugou <歌名> - 酷狗音乐搜索§a.", player);
                MessageUtils.sendNormalMessage("/" + cmdName + " search kuwo <歌名> - 酷我音乐搜索§a.", player);
                MessageUtils.sendNormalMessage("/" + cmdName + " search bilibili <:auXXXXX歌名> - 哔哩哔哩音乐搜索§a.", player);
                MessageUtils.sendNormalMessage("§6=========================================", player);
                break;
            case "url":
                MessageUtils.sendNormalMessage("/" + cmdName + " url <音乐直链> - 播放链接的音乐§a.", player);
                break;
            default:
                MessageUtils.sendNull(cmdName, player);
        }
    }
}
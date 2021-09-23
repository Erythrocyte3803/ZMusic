package me.zhenxin.zmusic.module.command.impl

import me.zhenxin.zmusic.module.Config
import me.zhenxin.zmusic.module.Lang
import me.zhenxin.zmusic.module.taboolib.sendMsg
import me.zhenxin.zmusic.utils.setLocale
import taboolib.common.platform.ProxyCommandSender
import taboolib.common.platform.command.subCommand
import taboolib.module.lang.Language

/**
 * 重载命令
 *
 * @author 真心
 * @since 2021/9/23 11:11
 * @email qgzhenxin@qq.com
 */
val reloadCommand = subCommand {
    execute<ProxyCommandSender> { sender, _, _ ->
        Config.reload()
        Language.reload()
        setLocale()
        sender.sendMsg(Lang.COMMAND_RELOAD_SUCCESS)
    }
}
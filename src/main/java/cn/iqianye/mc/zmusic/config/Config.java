package cn.iqianye.mc.zmusic.config;

import cn.iqianye.mc.zmusic.Main;
import cn.iqianye.mc.zmusic.other.Val;
import cn.iqianye.mc.zmusic.utils.LogUtils;
import cn.iqianye.mc.zmusic.utils.NetUtils;
import cn.iqianye.mc.zmusic.utils.OtherUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class Config {
    // Prefix
    public static String prefix = ChatColor.AQUA + "ZMusic " + ChatColor.YELLOW + ">>> " + ChatColor.RESET;
    // Version
    public static int version;
    // LatestVersion
    public static int latestVersion = 7;
    // Debug
    public static boolean debug;
    // Update
    public static boolean update;
    // Api
    public static String neteaseApiRoot;
    public static String qqMusicApiRoot;
    // Account
    // Netease
    public static String neteaseloginType;
    public static String neteaseAccount;
    public static String neteasePassword;
    public static boolean neteaseFollow;
    public static String neteasePasswordType;
    // Bilibili
    public static String bilibiliQQ;
    public static String bilibiliKey;
    // Music
    public static int money;
    public static int cooldown;
    // Lyric
    public static boolean lyricEnable;
    public static boolean showLyricTr;
    public static String lyricColor;
    public static boolean supportBossBar = false;
    public static boolean supportActionBar = false;
    public static boolean supportTitle = false;
    public static boolean supportChat = false;
    public static boolean supportHud = false;

    // RealSupport
    public static boolean realSupportBossBar = true;
    public static boolean realSupportTitle = true;
    public static boolean realSupportActionBar = true;
    public static boolean realSupportVault = true;
    public static boolean realSupportAdvancement = true;
    public static boolean realSupportHud = true;

    public static void load(FileConfiguration configuration) {
        // Version
        version = configuration.getInt("version");
        if (version != latestVersion) {
            LogUtils.sendNormalMessage("-- 正在更新配置文件...");
            File config = new File(JavaPlugin.getPlugin(Main.class).getDataFolder() + File.separator + "config.yml");
            File configBak = new File(JavaPlugin.getPlugin(Main.class).getDataFolder() + File.separator + "config.yml.v" + version + ".bak");
            LogUtils.sendNormalMessage("-- 正在备份原配置文件...");
            config.renameTo(configBak);
            LogUtils.sendNormalMessage("-- 正在释放新配置文件...");
            JavaPlugin.getPlugin(Main.class).saveDefaultConfig();
            LogUtils.sendNormalMessage("-- 更新完毕.");
            JavaPlugin.getPlugin(Main.class).reloadConfig();
        }
        // Debug
        debug = configuration.getBoolean("debug");
        update = configuration.getBoolean("update");
        // Prefix
        prefix = ChatColor.translateAlternateColorCodes('&', configuration.getString("prefix"));
        // Api
        neteaseApiRoot = configuration.getString("api.netease");
        qqMusicApiRoot = configuration.getString("api.qq");
        // Account
        // Netease
        neteaseloginType = configuration.getString("account.netease.loginType");
        neteaseAccount = configuration.getString("account.netease.account");
        neteasePasswordType = configuration.getString("account.netease.passwordType");
        if (neteasePasswordType.equalsIgnoreCase("normal")) {
            neteasePassword = OtherUtils.getMD5String(configuration.getString("account.netease.password"));
        } else if (neteasePasswordType.equalsIgnoreCase("md5")) {
            neteasePassword = configuration.getString("account.netease.password");
        }
        neteaseFollow = configuration.getBoolean("account.netease.follow");
        // Bilibili
        bilibiliQQ = configuration.getString("account.bilibili.qq");
        bilibiliKey = configuration.getString("account.bilibili.key");
        if (!bilibiliKey.equalsIgnoreCase("none")) {
            new Thread(() -> {
                Gson gson = new GsonBuilder().create();
                String jsonText = NetUtils.getNetString("https://api.zhenxin.xyz/minecraft/plugins/ZMusic/bilibili/checkVIP.php?qq=" + bilibiliQQ + "&key=" + bilibiliKey, null);
                JsonObject json = gson.fromJson(jsonText, JsonObject.class);
                Val.bilibiliIsVIP = json.get("isVIP").getAsBoolean();
            }).start();
        }
        // Music
        money = configuration.getInt("music.money");
        cooldown = configuration.getInt("music.cooldown");
        // Lyric
        lyricEnable = configuration.getBoolean("lyric.enable");
        showLyricTr = configuration.getBoolean("lyric.showLyricTr");
        lyricColor = ChatColor.translateAlternateColorCodes('&', configuration.getString("lyric.color"));
        if (realSupportBossBar) {
            supportBossBar = configuration.getBoolean("lyric.bossBar");
        }
        if (realSupportActionBar) {
            supportActionBar = configuration.getBoolean("lyric.actionBar");
        }
        if (realSupportTitle) {
            supportTitle = configuration.getBoolean("lyric.subTitle");
        }
        if (realSupportHud) {
            supportHud = configuration.getBoolean("lyric.hud");
        }
        supportChat = configuration.getBoolean("lyric.chatMessage");
    }
}
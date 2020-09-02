package cn.iqianye.mc.zmusic.api;

import cn.iqianye.mc.zmusic.config.Config;
import cn.iqianye.mc.zmusic.utils.LogUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.advancement.Advancement;
import org.bukkit.advancement.AdvancementProgress;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;

public class AdvancementAPI {

    private NamespacedKey id;
    private String icon;
    private String title, description;
    private String frame = "task";
    private boolean announce = false, toast = true;


    private JavaPlugin pl;

    String c4RootName = "minecraft:record";
    Version version = new Version();
    private String[] strings = new String[]{
            c4RootName + "_13",
            c4RootName + "_cat",
            c4RootName + "_blocks",
            c4RootName + "_chirp",
            c4RootName + "_far",
            c4RootName + "_mall",
            c4RootName + "_mellohi",
            c4RootName + "_strad",
            c4RootName + "_ward",
            c4RootName + "_wait"};

    {
        if (version.isHigherThan("1.13")) {
            c4RootName = "minecraft:music_disc";
        }
    }

    public AdvancementAPI(String id, String title, JavaPlugin pl) {
        this(new NamespacedKey(pl, id), title, pl);
    }

    public AdvancementAPI(NamespacedKey id, String title, JavaPlugin pl) {
        this.id = id;
        this.title = title;
        this.description = "Zmusic 专用成就";
        Random random = new Random();
        int r = random.nextInt(strings.length);
        String icon = strings[r];
        if (Config.debug) {
            LogUtils.sendDebugMessage("[进度] 随机图标: " + icon);
        }
        this.icon = icon;
        this.pl = pl;
    }

    public AdvancementAPI(NamespacedKey id, String title, JavaPlugin pl, String frame) {
        this.id = id;
        this.title = title;
        this.description = "Zmusic 专用成就";
        Random random = new Random();
        int r = random.nextInt(strings.length);
        String icon = strings[r];
        if (Config.debug) {
            LogUtils.sendDebugMessage("[进度] 随机图标: " + icon);
        }
        this.icon = icon;
        this.pl = pl;
        this.frame = frame;
    }

    @SuppressWarnings("deprecation")
    private void add() {
        new BukkitRunnable() {
            @Override
            public void run() {
                try {
                    Bukkit.getUnsafe().loadAdvancement(id, getJson());
                    if (Config.debug) {
                        LogUtils.sendDebugMessage("[进度] 进度 " + id + " 已保存");
                    }
                } catch (IllegalArgumentException e) {
                    if (Config.debug) {
                        LogUtils.sendDebugMessage("[进度] 保存失败：ID " + id + " 已存在");
                    }
                }
            }
        }.runTaskAsynchronously(pl);
    }

    @SuppressWarnings("deprecation")
    private void remove() {
        Bukkit.getUnsafe().removeAdvancement(id);
        Bukkit.reloadData();
        if (Config.debug) {
            LogUtils.sendDebugMessage("[进度] 进度 " + id + " 删除");
        }
    }

    //给予成就
    private void grant(Player p) {
        new BukkitRunnable() {
            @Override
            public void run() {
                Advancement advancement = Bukkit.getAdvancement(id);
                AdvancementProgress progress;
                progress = p.getAdvancementProgress(advancement);
                if (!progress.isDone()) {
                    for (String criteria : progress.getRemainingCriteria()) {
                        progress.awardCriteria(criteria);
                    }
                }
            }
        }.runTaskAsynchronously(pl);
    }

    public String getJson() {
        JsonObject json = new JsonObject();
        JsonObject icon = new JsonObject();
        icon.addProperty("item", this.icon);
        JsonObject display = new JsonObject();
        display.add("icon", icon);
        display.addProperty("title", this.title);
        display.addProperty("description", this.description);
        display.addProperty("background", "minecraft:textures/gui/advancements/backgrounds/adventure.png");
        display.addProperty("frame", this.frame);
        display.addProperty("announce_to_chat", announce);
        display.addProperty("show_toast", toast);
        display.addProperty("hidden", true);

        JsonObject criteria = new JsonObject();
        JsonObject trigger = new JsonObject();

        trigger.addProperty("trigger", "minecraft:impossible");
        criteria.add("impossible", trigger);

        json.add("criteria", criteria);
        json.add("display", display);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        return gson.toJson(json);
    }


    public void sendAdvancement(Player p) {
        new BukkitRunnable() {
            @Override
            public void run() {
                add();
                grant(p);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                remove();
            }
        }.runTaskAsynchronously(pl);
    }
}
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4.10" // Kotlin JVM
    id("com.github.johnrengelman.shadow") version "6.1.0" // 全量打包插件
}
group = "me.zhenxin"
version = "3.0"

repositories {
    mavenLocal()
    maven {
        name = "AliyunJCenter"
        setUrl("https://maven.aliyun.com/repository/public")
    }

    maven {
        name = "Spigot"
        setUrl("https://hub.spigotmc.org/nexus/content/groups/public/")
    }
    maven {
        name = "PlaceholderAPI"
        setUrl("https://repo.extendedclip.com/content/repositories/placeholderapi/")
    }
    maven {
        name = "jitpack.io"
        setUrl("https://jitpack.io")
    }
    maven {
        name = "ZhenXin"
        setUrl("https://code.aliyun.com/zhenxin/Maven/raw/master/")
    }
    maven {
        name = "ViaVersion"
        setUrl("https://repo.viaversion.com/")
    }
    jcenter()
}
dependencies {

    compileOnly("org.spigotmc", "spigot-api", "1.16.4-R0.1-SNAPSHOT") // Spigot API
    testImplementation("org.spigotmc", "spigot-api", "1.16.4-R0.1-SNAPSHOT") // Spigot API - 测试环境
    compileOnly("net.md-5", "bungeecord-api", "1.16-R0.4-SNAPSHOT") // BC API

    // NMS
    compileOnly("org.bukkit.nms", "v1_8_R3", "1")
    compileOnly("org.bukkit.nms", "v1_12_R1", "1")
    compileOnly("org.bukkit.nms", "v1_13_R2", "1")
    compileOnly("org.bukkit.nms", "v1_14_R1", "1")
    compileOnly("org.bukkit.nms", "v1_15_R1", "1")
    compileOnly("org.bukkit.nms", "v1_16_R1", "1")
    compileOnly("org.bukkit.nms", "v1_16_R2", "1")

    compileOnly("me.clip", "placeholderapi", "2.9.2") // PAPI
    compileOnly("com.github.MilkBowl", "VaultAPI", "1.7") // Vault
    compileOnly("us.myles", "viaversion", "3.1.0") // ViaVersion
    compileOnly("io.netty", "netty-buffer", "4.1.51.Final") // netty-buffer
    testImplementation(kotlin("test-junit")) // 测试环境
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

tasks.build {
    // build时执行shadowJar任务
    dependsOn(tasks.shadowJar)
}


tasks.processResources {
    // 替换版本
    from("src/main/resources/bungee.yml") {
        // BC插件版本
        filter { return@filter it.replace("%version%", version.toString()) }
    }

    from("src/main/resources/plugin.yml") {
        // Bukkit插件版本
        filter { return@filter it.replace("%version%", version.toString()) }
    }
}


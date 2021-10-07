import org.jetbrains.kotlin.gradle.tasks.KotlinCompile


plugins {
    kotlin("jvm") version "1.5.31"
    id("loomcn") version "0.9-SNAPSHOT"
    `maven-publish`
}

java.sourceCompatibility = JavaVersion.VERSION_16
java.targetCompatibility = JavaVersion.VERSION_16

val archivesBaseName = Version.archives_base_name

version = Version.mod_version
group = Version.maven_group

repositories {

}

dependencies {
    // To change the versions see the gradle.properties file
    minecraft("com.mojang:minecraft:${Version.minecraft_version}")
    mappings("net.fabricmc:yarn:${Version.yarn_mappings}:v2")
    modImplementation("net.fabricmc:fabric-loader:${Version.loader_version}")

    // Fabric API. This is technically optional, but you probably want it anyway.
    modImplementation("net.fabricmc.fabric-api:fabric-api:${Version.fabric_version}")

    // PSA: Some older mods, compiled on Loom 0.2.1, might have outdated Maven POMs.
    // You may need to force-disable transitiveness on them.

    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    testImplementation(kotlin("test"))
}

tasks.processResources {
    duplicatesStrategy = DuplicatesStrategy.INCLUDE
    from("src/main/resources/fabric.mod.json") {
        filter { return@filter it.replace("\${version}", version.toString()) }
    }
}

tasks.test {
    useJUnit()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "16"
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

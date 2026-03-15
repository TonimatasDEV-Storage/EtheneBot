import kotlin.io.path.createDirectory
import kotlin.io.path.exists

plugins {
    application
    `embedded-kotlin`
    id("com.gradleup.shadow") version "9.3.2"
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

group = "dev.tonimatas"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("net.dv8tion:JDA:6.3.1") {
        exclude(module = "opus-java")
    }

    // https://github.com/qos-ch/logback/releases
    implementation("ch.qos.logback:logback-classic:1.5.32")
}

application {
    mainClass.set("dev.tonimatas.ethene.Main")
}

tasks.run {
    val path = rootDir.toPath().resolve("run")
    workingDir = path.toFile()
    if (!path.exists()) path.createDirectory()
}

tasks.compileJava {
    options.encoding = "UTF-8"
    java.sourceCompatibility = JavaVersion.VERSION_21
    java.targetCompatibility = JavaVersion.VERSION_21
}

tasks.jar {
    finalizedBy(tasks.shadowJar)
    archiveClassifier.set("plain")
}

tasks.shadowJar {
    archiveClassifier.set("")

    minimize {
        exclude(dependency("ch.qos.logback:logback-classic:.*"))
    }

    manifest {
        attributes("Main-Class" to "dev.tonimatas.botstudio.Main")
    }
}

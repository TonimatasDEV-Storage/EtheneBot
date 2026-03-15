package dev.ethenebot.tonimatas

import dev.ethenebot.tonimatas.data.BotData
import dev.ethenebot.tonimatas.data.BotToken
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.entities.Activity
import net.dv8tion.jda.api.requests.GatewayIntent
import net.dv8tion.jda.api.utils.MemberCachePolicy
import net.dv8tion.jda.api.utils.cache.CacheFlag
import net.dv8tion.jda.internal.utils.JDALogger
import org.slf4j.Logger

class Main {
    init {
        val time = System.currentTimeMillis()
        
        logger.info("Initializing bot")
        BotData.runProperties()
        
        val token = BotToken.getToken()
        
        val jda = JDABuilder.createDefault(token)
            .enableIntents(GatewayIntent.entries)
            .enableCache(CacheFlag.entries)
            .setBulkDeleteSplittingEnabled(false)
            .setMemberCachePolicy(MemberCachePolicy.ALL)
            .addEventListeners()
            .setActivity(Activity.playing("Minecraft"))
            .setAutoReconnect(true)
            .build()
        
        jda.awaitReady()
        
        addStopHook(jda)
        
        logger.info("Done! ({}s)", ((System.currentTimeMillis() - time) / 1000).toFloat())
    }

    fun addStopHook(jda: JDA) {
        Runtime.getRuntime().addShutdownHook(Thread {
            logger.info("Stopping...")

            jda.shutdown()
            jda.awaitShutdown()

            logger.info("Stopped!")
        })
    }
    
    companion object {
        var logger: Logger = JDALogger.getLog(Main::class.java)

        @JvmStatic
        fun main(args: Array<String>) {
            Main()
        }
    }
}
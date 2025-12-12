package dev.ethenebot.tonimatas

import net.dv8tion.jda.internal.utils.JDALogger
import org.slf4j.Logger

class Main {
    init {
        logger.info("Initializing bot")
    }
    
    
    companion object {
        var logger: Logger = JDALogger.getLog(Main::class.java)

        @JvmStatic
        fun main(args: Array<String>) {
            Main()
        }
    }
}
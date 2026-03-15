package dev.ethenebot.tonimatas.data

import net.dv8tion.jda.internal.utils.JDALogger
import org.slf4j.Logger
import java.io.File
import java.io.FileNotFoundException
import java.io.IOException
import java.util.Scanner

class BotToken {
    companion object {
        var logger: Logger = JDALogger.getLog(BotToken::class.java)
        
        @JvmStatic
        fun getToken(): String {
            val file = File("token.txt")

            if (!file.exists()) {
                try {
                    file.createNewFile()
                    logger.error("You need to add key file in the file \"token.txt\".")
                } catch (e: IOException) {
                    logger.error("Error on create key file: {}", e.message)
                }

                throw RuntimeException("Invalid token")
            }

            val scanner: Scanner

            try {
                scanner = Scanner(file)
            } catch (e: FileNotFoundException) {
                logger.error("Error reading the token file: {}", e.message)
                throw RuntimeException("Invalid token")
            }

            if (!scanner.hasNext()) {
                logger.error("You need to add key file in the file \"token.txt\".")
                throw RuntimeException("Empty token")
            }
            
            return scanner.next()
        }
    }
}
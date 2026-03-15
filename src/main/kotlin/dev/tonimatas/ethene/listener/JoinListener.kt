package dev.tonimatas.ethene.listener

import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import net.dv8tion.jda.internal.utils.JDALogger
import org.slf4j.Logger

class JoinListener : ListenerAdapter() {
    var logger: Logger = JDALogger.getLog(JoinListener::class.java)

    // Real
    //var channelId = "1323369443145027738"
    //var roleId = "1323370508477005865"

    // Testing
    val channelId = "1471176961735790735"
    val roleId = "1471920290673201235"

    override fun onGuildMemberJoin(event: GuildMemberJoinEvent) {
        val guild = event.guild
        val channel = guild.getTextChannelById(channelId)
        val role = guild.getRoleById(roleId)
        val member = event.member

        channel?.sendMessage(member.asMention + " Welcome to Ethene Hosting! We already are: " + guild.memberCount + "!")
            ?.queue()

        if (role != null) {
            guild.addRoleToMember(member, role).queue()
        }

        logger.info("{} joined. Count: {}", member.effectiveName, guild.memberCount)
    }
}
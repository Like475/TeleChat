package io.github.like475.telechat.processor

import com.github.kotlintelegrambot.entities.User
import io.github.like475.telechat.TeleChat
import io.github.like475.telechat.telegram.TelegramBot
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.minimessage.MiniMessage
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer
import org.bukkit.advancement.Advancement
import org.bukkit.entity.Player

object Processor {
    private lateinit var teleChat: TeleChat

    fun init(token: String, chatId: Long, teleChat: TeleChat) {
        TelegramBot.init(token, chatId)
        this.teleChat = teleChat
    }

    fun processTelegramMessage(user: User, message: String) {
        teleChat.sendMessage(
            MiniMessage
            .miniMessage()
            .deserialize(
                "<gradient:aqua:dark_aqua>${user.username?.replace('_', ' ')}</gradient> "
                        + "<dark_gray>[<dark_aqua>TG</dark_aqua>></dark_gray> $message"))
    }

    fun processMinecraftMessage(player: Player, message: Component) {
        val messageText = PlainTextComponentSerializer.plainText().serialize(message)
        TelegramBot.sendMessage("*${player.name.replace('_', ' ')}* _MESSAGE_\n`$messageText`")
    }

    fun processPlayerJoinLeave(player: Player, isJoin: Boolean) {
        if (isJoin) {
            TelegramBot.sendMessage("*${player.name.replace('_', ' ')}* _JOIN_")
        } else {
            TelegramBot.sendMessage("*${player.name.replace('_', ' ')}* _QUIT_")
        }
    }

    fun processPlayerAdvancementDone(player: Player, advancement: Advancement) {
        val advancementName = PlainTextComponentSerializer
            .plainText()
            .serialize(advancement.displayName())
            .removeSurrounding("[", "]")
        TelegramBot.sendMessage("*${player.name.replace('_', ' ')}* _ADVANCEMENT_\n`$advancementName`")
    }

    fun processPlayerDeath(player: Player, deathMessage: Component?) {
        var deathMessageText = ""
        if (deathMessage != null) {
            deathMessageText = PlainTextComponentSerializer
                .plainText()
                .serialize(deathMessage)
                .removeSurrounding("${player.name.replace('_', ' ')} ", "")
        }
        TelegramBot.sendMessage("*${player.name.replace('_', ' ')}* _DEATH_\n`$deathMessageText`")
    }
}
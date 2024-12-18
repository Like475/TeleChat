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
                "<gradient:aqua:dark_aqua>${user.username}</gradient> "
                        + "<dark_gray>[<dark_aqua>TG</dark_aqua>></dark_gray> $message"))
    }

    fun processMinecraftMessage(player: Player, message: Component) {
        val messageText = PlainTextComponentSerializer.plainText().serialize(message)
        TelegramBot.sendMessage("${player.name}: $messageText")
    }

    fun processPlayerJoinLeave(player: Player, isJoin: Boolean) {
        if (isJoin) {
            TelegramBot.sendMessage("${player.name} присоеденился к серверу")
        } else {
            TelegramBot.sendMessage("${player.name} покинул сервер")
        }
    }

    fun processPlayerAdvancementDone(player: Player, advancement: Advancement) {
        val advancementName = PlainTextComponentSerializer
            .plainText()
            .serialize(advancement.displayName())
            .removeSurrounding("[", "]")
        TelegramBot.sendMessage("${player.name} выполнил достижение: $advancementName")
    }

    fun processPlayerDeath(player: Player, deathMessage: Component?) {
        var deathMessageText = ""
        if (deathMessage != null) {
            deathMessageText = " по причине: " + PlainTextComponentSerializer
                .plainText()
                .serialize(deathMessage)
                .removeSurrounding("${player.name} ", "")
        }
        TelegramBot.sendMessage("${player.name} умер$deathMessageText")
    }
}
package io.github.like475.teleChat

import io.papermc.paper.event.player.AsyncChatEvent
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin

class TeleChat : JavaPlugin(), Listener {

    override fun onEnable() {
        saveDefaultConfig()
        val token = config.getString("token")
        val chatId = config.getLong("chat-id")
        if (token == "token" || chatId.toInt() == 0) {
            return
        }
        TelegramBot.init(token!!, chatId)
        server.pluginManager.registerEvents(this, this)
        TelegramBot.setMessageCallback { message ->
            server.sendMessage(Component.text(message))
        }
    }

    @EventHandler
    fun onChat(event: AsyncChatEvent) {
        val message = PlainTextComponentSerializer.plainText().serialize(event.message())
        val playerName = event.player.name
        TelegramBot.send("$playerName: $message")
    }
}

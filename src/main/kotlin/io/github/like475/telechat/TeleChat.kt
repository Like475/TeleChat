package io.github.like475.telechat

import io.github.like475.telechat.handlers.ChatHandler
import io.github.like475.telechat.handlers.PlayerAdvancementDoneHandler
import io.github.like475.telechat.handlers.PlayerDeathHandler
import io.github.like475.telechat.handlers.PlayerJoinQuitHandler
import io.github.like475.telechat.processor.Processor
import net.kyori.adventure.text.Component
import org.bukkit.plugin.java.JavaPlugin

class TeleChat : JavaPlugin() {
    override fun onEnable() {
        saveDefaultConfig()
        val token = config.getString("token")
        val chatId = config.getLong("chat-id")
        if (token != "token" && token != null && chatId.toInt() != 0) {
            Processor.init(token, chatId, this)

            server.pluginManager.registerEvents(ChatHandler, this)
            server.pluginManager.registerEvents(PlayerJoinQuitHandler, this)
            server.pluginManager.registerEvents(PlayerAdvancementDoneHandler, this)
            server.pluginManager.registerEvents(PlayerDeathHandler, this)
        }
    }

    fun sendMessage(message: Component) {
        server.sendMessage(message)
    }
}

package io.github.like475.telechat.handlers

import io.github.like475.telechat.processor.Processor
import io.papermc.paper.event.player.AsyncChatEvent
import net.kyori.adventure.text.minimessage.MiniMessage
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener

object ChatHandler : Listener {
    @EventHandler
    fun onChat(event: AsyncChatEvent) {
        event.renderer { source, _, message, _ ->
            MiniMessage
                .miniMessage()
                .deserialize("<gradient:green:dark_green>${source.name}</gradient> <dark_gray>[></dark_gray> ")
                .append(message)
        }
        Processor.processMinecraftMessage(event.player, event.message())
    }
}
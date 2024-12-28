package io.github.like475.telechat.handlers

import io.github.like475.telechat.processor.Processor
import net.kyori.adventure.text.minimessage.MiniMessage
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.PlayerDeathEvent


object PlayerDeathHandler : Listener {
    @EventHandler
    fun onPlayerDeath(event: PlayerDeathEvent) {
        val originalMessage = event.deathMessage()
        if (originalMessage != null) {
            val originalMessageText = PlainTextComponentSerializer
                .plainText()
                .serialize(originalMessage)
                .removeSurrounding("${event.player.name} ", "")
            event.deathMessage(
                MiniMessage
                    .miniMessage()
                    .deserialize("<gradient:green:dark_green>${event.player.name}</gradient> "
                            + "<dark_gray>[<dark_red>DTH</dark_red>></dark_gray> $originalMessageText")
            )
        }
        Processor.processPlayerDeath(event.player, originalMessage)
    }
}
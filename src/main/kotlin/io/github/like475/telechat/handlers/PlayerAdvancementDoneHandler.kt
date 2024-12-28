package io.github.like475.telechat.handlers

import io.github.like475.telechat.processor.Processor
import net.kyori.adventure.text.minimessage.MiniMessage
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerAdvancementDoneEvent


object PlayerAdvancementDoneHandler : Listener {
    @EventHandler
    fun onPlayerAdvancementDone(event: PlayerAdvancementDoneEvent) {
        val originalDisplayName = event.advancement.display?.displayName() ?: return
        val displayNameText = "<gold>" + MiniMessage
            .miniMessage()
            .serialize(originalDisplayName)
            .removeSurrounding("<green>", "")

        event.message(
            MiniMessage.miniMessage().deserialize(
                "<gradient:green:dark_green>${event.player.name}</gradient>"
                        + " <dark_gray>[<blue>ADV</blue>></dark_gray> $displayNameText"
            )
        )

        Processor.processPlayerAdvancementDone(event.player, event.advancement)
    }
}
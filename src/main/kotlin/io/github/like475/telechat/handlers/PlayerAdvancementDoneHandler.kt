package io.github.like475.telechat.handlers

import io.github.like475.telechat.processor.Processor
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerAdvancementDoneEvent

object PlayerAdvancementDoneHandler : Listener {
    @EventHandler
    fun onPlayerAdvancementDone(event: PlayerAdvancementDoneEvent) {
        if (event.message() == null) return
        Processor.processPlayerAdvancementDone(event.player, event.advancement)
    }
}
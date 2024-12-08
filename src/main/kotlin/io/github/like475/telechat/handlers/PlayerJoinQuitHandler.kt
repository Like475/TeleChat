package io.github.like475.telechat.handlers

import io.github.like475.telechat.processor.Processor
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent

object PlayerJoinQuitHandler : Listener {
    @EventHandler
    fun onPlayerJoin(event: PlayerJoinEvent) {
        Processor.processPlayerJoinLeave(event.player, true)
    }

    @EventHandler
    fun onPlayerQuit(event: PlayerQuitEvent) {
        Processor.processPlayerJoinLeave(event.player, false)
    }
}
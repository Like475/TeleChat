package io.github.like475.telechat.handlers

import io.github.like475.telechat.processor.Processor
import net.kyori.adventure.text.minimessage.MiniMessage
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent

object PlayerJoinQuitHandler : Listener {
    @EventHandler
    fun onPlayerJoin(event: PlayerJoinEvent) {
        event.joinMessage(
            MiniMessage
                .miniMessage()
                .deserialize("<gradient:green:dark_green>${event.player.name}</gradient> "
                        + "<dark_gray>[<green>JOIN</green>]</dark_gray>")
        )

        Processor.processPlayerJoinLeave(event.player, true)
    }

    @EventHandler
    fun onPlayerQuit(event: PlayerQuitEvent) {
        event.quitMessage(
            MiniMessage
                .miniMessage()
                .deserialize("<gradient:green:dark_green>${event.player.name}</gradient> "
                        + "<dark_gray>[<red>QUIT</red>]</dark_gray>")
        )

        Processor.processPlayerJoinLeave(event.player, false)
    }
}
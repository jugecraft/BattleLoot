package com.battleloot.events;

import com.battleloot.BattleLoot;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

public class VictoryListener implements Listener {

    private final BattleLoot plugin;

    public VictoryListener(BattleLoot plugin) {
        this.plugin = plugin;  // Guarda la instancia del plugin
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        // Código para manejar el evento cuando un jugador se une
    }
}
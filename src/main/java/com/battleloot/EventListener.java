package com.battleloot;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class EventListener implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getClickedBlock() == null || event.getClickedBlock().getType() != org.bukkit.Material.CHEST) {
            return;  // Solo nos interesa si el jugador hace clic en un cofre
        }

        ItemStack item = event.getItem();
        if (item != null && item.getType() == org.bukkit.Material.DIAMOND) {
            // Ejemplo de que los jugadores reciben un loot o beneficio al interactuar con cofres
            event.getPlayer().sendMessage("¡Has encontrado un tesoro!");
        }
    }
}

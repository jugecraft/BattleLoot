package com.battleloot.events;

import com.battleloot.BattleLoot;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.entity.Player;

public class KillListener implements Listener {

    private final BattleLoot plugin;

    public KillListener(BattleLoot plugin) {
        this.plugin = plugin;  // Guarda la instancia del plugin
    }

    @EventHandler
    public void onPlayerKill(EntityDeathEvent event) {
        if (event.getEntity() instanceof Player) {
            Player victim = (Player) event.getEntity();
            Player killer = victim.getKiller();

            if (killer != null && killer instanceof Player) {
                plugin.getServer().broadcastMessage("§a" + killer.getName() + " ha matado a " + victim.getName());
                // Resto del código relacionado con estadísticas y efectos.
            }
        }
    }
}

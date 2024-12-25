package com.battleloot.utils;

import com.battleloot.BattleLoot;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.World;
import org.bukkit.Effect;
import org.bukkit.util.Vector;

public class KillEffects implements Listener {

    private final BattleLoot plugin;

    public KillEffects(BattleLoot plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerKill(EntityDeathEvent event) {
        Entity entity = event.getEntity();  // La entidad que ha muerto (puede ser un jugador)
        if (entity instanceof Player) {
            Player killedPlayer = (Player) entity;
            Player killer = killedPlayer.getKiller();
            if (killer != null && killer instanceof Player) {
                // Efectos de la muerte cuando un jugador mata a otro
                triggerKillEffects(killer, killedPlayer);
            }
        }
    }

    private void triggerKillEffects(Player killer, Player killedPlayer) {
        World world = killer.getWorld();

        // Efectos de partículas alrededor del killer (jugador que mató) - usando un efecto de explosión.
        world.playEffect(killer.getLocation(), Effect.EXPLOSION_LARGE, 0);

        // Sonido para el killer, al hacer un asesinato.
        killer.playSound(killer.getLocation(), Sound.EXPLODE, 1.0f, 1.0f);

        // Enviar mensaje de "asesinato".
        killer.sendMessage("§a¡Has matado a " + killedPlayer.getName() + "!");

        // Efecto para el jugador muerto: efectos de polvo
        world.playEffect(killedPlayer.getLocation(), Effect.SMOKE, 0);

        // Sonido para el jugador muerto, para representar daño.
        killedPlayer.playSound(killedPlayer.getLocation(), Sound.HURT_FLESH, 1.0f, 1.0f);
    }
}
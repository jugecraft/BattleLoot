package com.battleloot.utils;

import com.battleloot.BattleLoot;
import com.battleloot.events.PlayerVictoryEvent;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.util.Vector;

public class VictoryEffects implements Listener {

    private final BattleLoot plugin;

    public VictoryEffects(BattleLoot plugin) {
        this.plugin = plugin;
    }

    // Este método se dispara cuando un jugador gana
    @EventHandler
    public void onPlayerVictory(PlayerVictoryEvent event) {
        Player winner = event.getWinner(); // El jugador que ganó

        // Disparar efectos de victoria para el jugador ganador
        triggerVictoryEffects(winner);
    }

    // Aplicamos los efectos al jugador ganador
    private void triggerVictoryEffects(Player winner) {
        // Generar un efecto visual de partículas tipo EXPLOSION_LARGE en la ubicación del jugador ganador
        winner.getWorld().playEffect(winner.getLocation(), org.bukkit.Effect.EXPLOSION_LARGE, 0);

        // Reproducir el sonido de nivel alcanzado para el jugador
        winner.playSound(winner.getLocation(), Sound.LEVEL_UP, 1.0f, 1.0f);

        // Enviar mensaje de victoria al jugador
        winner.sendMessage("§a¡Felicidades! ¡Has ganado la partida!");

        // Podrías agregar más efectos aquí (por ejemplo, un pequeño impulso)
        winner.setVelocity(new Vector(0, 0.5, 0));  // Impulsar al jugador para efectos visuales
    }
}
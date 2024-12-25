package com.battleloot.managers;

import com.battleloot.BattleLoot;
import com.battleloot.models.PlayerStats;
import org.bukkit.entity.Player;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;
import java.util.Map;

public class StatsManager {

    private final BattleLoot plugin;
    private Map<String, PlayerStats> playerStatsMap;

    public StatsManager(BattleLoot plugin) {
        this.plugin = plugin;
        this.playerStatsMap = new HashMap<>();
        loadStats();
    }

    // Cargar estadísticas desde un archivo (por ejemplo, stats.yml)
    public void loadStats() {
        FileConfiguration config = plugin.getConfig();

        // Cargar las estadísticas de cada jugador desde la configuración, por ejemplo:
        // playerStatsMap.put(playerName, new PlayerStats(playerName, kills, deaths, victories));
    }

    // Actualizar las estadísticas de un jugador
    public void updateStats(Player player, String statType) {
        PlayerStats stats = playerStatsMap.get(player.getName());
        if (stats == null) {
            stats = new PlayerStats(player.getName(), 0, 0, 0); // Crea el objeto de estadísticas si no existe
            playerStatsMap.put(player.getName(), stats);
        }

        switch (statType) {
            case "kill":
                stats.setKills(stats.getKills() + 1);
                break;
            case "death":
                stats.setDeaths(stats.getDeaths() + 1);
                break;
            case "victory":
                stats.setVictories(stats.getVictories() + 1);
                break;
            default:
                break;
        }
        saveStats();
    }

    // Guardar estadísticas en el archivo
    public void saveStats() {
        FileConfiguration config = plugin.getConfig();
        for (PlayerStats stats : playerStatsMap.values()) {
            config.set("stats." + stats.getPlayerName() + ".kills", stats.getKills());
            config.set("stats." + stats.getPlayerName() + ".deaths", stats.getDeaths());
            config.set("stats." + stats.getPlayerName() + ".victories", stats.getVictories());
        }
        plugin.saveConfig();
    }

    // Obtener las estadísticas de un jugador
    public PlayerStats getStats(Player player) {
        return playerStatsMap.get(player.getName());
    }

    // Método para mostrar estadísticas al jugador (con un comando o mensaje)
    public void showStats(Player player) {
        PlayerStats stats = getStats(player);
        if (stats != null) {
            player.sendMessage("§7Estadísticas de " + player.getName() + ":");
            player.sendMessage("§7Asesinatos: " + stats.getKills());
            player.sendMessage("§7Muertes: " + stats.getDeaths());
            player.sendMessage("§7Victorias: " + stats.getVictories());
        } else {
            player.sendMessage("§cNo se encontraron estadísticas para " + player.getName());
        }
    }
}
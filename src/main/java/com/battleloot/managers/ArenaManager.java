package com.battleloot.managers;

import com.battleloot.BattleLoot;
import com.battleloot.models.Arena;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import java.util.HashMap;
import java.util.Map;

public class ArenaManager {

    private final BattleLoot plugin;
    private Map<String, Arena> arenas;

    public ArenaManager(BattleLoot plugin) {
        this.plugin = plugin;
        this.arenas = new HashMap<>();
        loadArenas();
    }

    /**
     * Carga todas las arenas configuradas en el archivo de configuración.
     */
    private void loadArenas() {
        FileConfiguration config = plugin.getConfig();
        if (config.contains("arenas")) {
            for (String arenaName : config.getConfigurationSection("arenas").getKeys(false)) {
                String path = "arenas." + arenaName;
                String lobbyPath = path + ".lobby";
                String spawnPath = path + ".spawn";

                Location lobby = (Location) config.get(lobbyPath);
                Location spawn = (Location) config.get(spawnPath);

                arenas.put(arenaName, new Arena(arenaName, lobby, spawn));
            }
        }
    }

    /**
     * Obtiene una arena por su nombre.
     *
     * @param arenaName Nombre de la arena.
     * @return Arena correspondiente o null si no existe.
     */
    public Arena getArena(String arenaName) {
        return arenas.get(arenaName);
    }

    /**
     * Crea una nueva arena y la guarda en el archivo de configuración.
     *
     * @param arenaName Nombre de la nueva arena.
     * @param lobby Ubicación del lobby.
     * @param spawn Ubicación del spawn de los jugadores.
     */
    public void createArena(String arenaName, Location lobby, Location spawn) {
        arenas.put(arenaName, new Arena(arenaName, lobby, spawn));
        saveArenaConfig(arenaName, lobby, spawn);
    }

    /**
     * Guarda los datos de la arena en el archivo de configuración.
     *
     * @param arenaName Nombre de la arena.
     * @param lobby Ubicación del lobby.
     * @param spawn Ubicación del spawn.
     */
    private void saveArenaConfig(String arenaName, Location lobby, Location spawn) {
        FileConfiguration config = plugin.getConfig();
        String path = "arenas." + arenaName;
        config.set(path + ".lobby", lobby);
        config.set(path + ".spawn", spawn);
        plugin.saveConfig();
    }

    /**
     * Teletransporta a un jugador al spawn de la arena especificada.
     *
     * @param player El jugador a teletransportar.
     * @param arenaName El nombre de la arena.
     */
    public void teleportToArena(Player player, String arenaName) {
        Arena arena = getArena(arenaName);
        if (arena != null) {
            player.teleport(arena.getSpawn());
            player.sendMessage("§aTe has teletransportado al spawn de la arena " + arenaName);
        } else {
            player.sendMessage("§cLa arena " + arenaName + " no existe.");
        }
    }

    /**
     * Un jugador se une a una arena.
     *
     * @param player El jugador que se une.
     * @param arenaName El nombre de la arena.
     */
    public void joinArena(Player player, String arenaName) {
        Arena arena = getArena(arenaName);
        if (arena != null) {
            teleportToArena(player, arenaName);
            player.sendMessage("§aTe has unido a la arena " + arenaName);
        } else {
            player.sendMessage("§cLa arena " + arenaName + " no está disponible.");
        }
    }
}
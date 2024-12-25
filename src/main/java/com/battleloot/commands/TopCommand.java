package com.battleloot.commands;

import com.battleloot.BattleLoot;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class TopCommand implements CommandExecutor {

    private final BattleLoot plugin;

    public TopCommand(BattleLoot plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // Solo permitir el comando a los jugadores
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Este comando solo puede ser ejecutado por un jugador.");
            return false;
        }

        Player player = (Player) sender;

        // Verificar si el jugador ha pasado un parámetro
        if (args.length == 0) {
            player.sendMessage(ChatColor.RED + "Debes especificar un tipo de estadística: 'victorias' o 'asesinatos'.");
            player.sendMessage(ChatColor.GREEN + "Uso correcto: /battlelootop <victorias|asesinatos>");
            return false;
        }

        // Recuperar el tipo de estadísticas (victorias o asesinatos)
        String topType = args[0].toLowerCase();

        Map<String, Integer> topStats = new HashMap<>();

        // Comprobar qué tipo de estadística se solicitó
        if (topType.equals("victorias")) {
            topStats = getTopByVictories();
        } else if (topType.equals("asesinatos")) {
            topStats = getTopByKills();
        } else {
            player.sendMessage(ChatColor.RED + "Tipo de estadística desconocida. Usa 'victorias' o 'asesinatos'.");
            return false;
        }

        // Mostrar el top de jugadores
        player.sendMessage(ChatColor.GOLD + "Top de " + topType + ":");

        int rank = 1;
        for (Map.Entry<String, Integer> entry : topStats.entrySet()) {
            String playerName = entry.getKey();
            int statValue = entry.getValue();
            player.sendMessage(ChatColor.GREEN + "#" + rank + " " + playerName + ": " + statValue);
            rank++;
        }

        return true;
    }

    // Obtener el top por victorias
    private Map<String, Integer> getTopByVictories() {
        Map<String, Integer> victories = new HashMap<>();

        for (String key : plugin.getConfig().getConfigurationSection("stats").getKeys(false)) {
            int victoriesCount = plugin.getConfig().getInt("stats." + key + ".victories");
            victories.put(key, victoriesCount);
        }

        return sortMapByValue(victories);
    }

    // Obtener el top por asesinatos
    private Map<String, Integer> getTopByKills() {
        Map<String, Integer> kills = new HashMap<>();

        for (String key : plugin.getConfig().getConfigurationSection("stats").getKeys(false)) {
            int killsCount = plugin.getConfig().getInt("stats." + key + ".kills");
            kills.put(key, killsCount);
        }

        return sortMapByValue(kills);
    }

    // Ordenar un mapa por valor
    private Map<String, Integer> sortMapByValue(Map<String, Integer> map) {
        Map<String, Integer> sortedMap = new HashMap<>();
        map.entrySet().stream()
                .sorted((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()))  // Orden descendente
                .forEachOrdered(entry -> sortedMap.put(entry.getKey(), entry.getValue()));
        return sortedMap;
    }
}

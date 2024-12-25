package com.battleloot.commands;

import com.battleloot.BattleLoot;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class StatsCommand implements CommandExecutor {

    private BattleLoot plugin;

    public StatsCommand(BattleLoot plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage("§cPor favor, especifica un jugador o un comando.");
            return false;
        }

        if (cmd.getName().equalsIgnoreCase("battlelootstats")) {
            String playerName = args[0];
            // Obtén las estadísticas del jugador de una base de datos o sistema
            sender.sendMessage("§aMostrando estadísticas de " + playerName);
            // Aquí deberías llamar un método de tu sistema para obtener estadísticas del jugador
        } else if (cmd.getName().equalsIgnoreCase("battlelootop")) {
            if (args.length == 2) {
                String category = args[0];
                if (category.equalsIgnoreCase("victorias") || category.equalsIgnoreCase("asesinatos")) {
                    String statType = args[1];
                    // Aquí deberías generar el ranking basándote en la categoría (victorias/asesinatos)
                    sender.sendMessage("§aMostrando ranking de " + category + ": " + statType);
                }
            }
        }

        return false;
    }
}

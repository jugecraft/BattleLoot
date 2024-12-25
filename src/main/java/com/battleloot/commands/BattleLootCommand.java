package com.battleloot.commands;

import com.battleloot.BattleLoot;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class BattleLootCommand implements CommandExecutor {

    private BattleLoot plugin;

    public BattleLootCommand(BattleLoot plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("battleloothelp")) {
            sender.sendMessage("§6Comandos de BattleLoot:");
            sender.sendMessage("§e/battlelootop <victorias|asesinatos> §7- Muestra el ranking.");
            sender.sendMessage("§e/battleloothelp §7- Muestra esta lista.");
            sender.sendMessage("§e/battlelootreload §7- Recarga la configuración.");
            sender.sendMessage("§e/battlelootsetLoot <default|advanced> §7- Configura el loot.");
            sender.sendMessage("§e/battlelootspawnchest <x> <y> <z> §7- Genera un cofre.");
            return true;
        }

        if (cmd.getName().equalsIgnoreCase("battlelootsetLoot")) {
            if (args.length == 0) {
                sender.sendMessage("§cPor favor, especifica el tipo de loot (default/advanced).");
                return false;
            }

            String lootType = args[0].toLowerCase();
            if (lootType.equals("default") || lootType.equals("advanced")) {
                plugin.getConfig().set("chest.loot.type", lootType);
                plugin.saveConfig();
                sender.sendMessage("§aTipo de loot actualizado a: " + lootType);
            } else {
                sender.sendMessage("§cTipo de loot no válido. Usa 'default' o 'advanced'.");
            }
            return true;
        }

        if (cmd.getName().equalsIgnoreCase("battlelootreload")) {
            plugin.reloadConfig();
            sender.sendMessage("§aConfiguración recargada correctamente.");
            return true;
        }

        return false;
    }
}
package com.battleloot.commands;

import com.battleloot.BattleLoot;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class KitCommand implements CommandExecutor {

    private BattleLoot plugin;

    public KitCommand(BattleLoot plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage("§cPor favor, especifica un subcomando.");
            return false;
        }

        if (cmd.getName().equalsIgnoreCase("battlelootkit")) {
            if (args[0].equalsIgnoreCase("create")) {
                if (args.length == 2) {
                    String kitName = args[1];
                    // Crea un nuevo kit y lo guarda en un sistema o base de datos
                    sender.sendMessage("§aKit " + kitName + " creado.");
                }
            } else if (args[0].equalsIgnoreCase("assign")) {
                if (args.length == 3) {
                    String kitName = args[1];
                    String playerName = args[2];
                    // Asigna el kit al jugador (puedes agregar lógica para verificar el jugador)
                    sender.sendMessage("§aKit " + kitName + " asignado a " + playerName);
                }
            }
        }

        return false;
    }
}

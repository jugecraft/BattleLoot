package com.battleloot.commands;

import com.battleloot.BattleLoot;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ArenaStartCommand implements CommandExecutor {

    private BattleLoot plugin;

    public ArenaStartCommand(BattleLoot plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 1) {
            String arenaName = args[0];
            // Aquí deberías iniciar la arena (comprobar que hay suficientes jugadores, etc.)
            sender.sendMessage("§aLa arena " + arenaName + " ha comenzado.");
        }
        return false;
    }
}

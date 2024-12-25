package com.battleloot.commands;

import com.battleloot.BattleLoot;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ArenaJoinCommand implements CommandExecutor {

    private BattleLoot plugin;

    public ArenaJoinCommand(BattleLoot plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 1) {
            String arenaName = args[0];
            // Aquí deberías verificar si la arena existe y permitir que el jugador se una
            sender.sendMessage("§aTe has unido a la arena " + arenaName + ".");
        }
        return false;
    }
}


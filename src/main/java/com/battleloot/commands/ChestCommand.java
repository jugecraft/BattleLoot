package com.battleloot.commands;

import com.battleloot.BattleLoot;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ChestCommand implements CommandExecutor {

    private BattleLoot plugin;

    public ChestCommand(BattleLoot plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cEste comando solo puede ser ejecutado por un jugador.");
            return false;
        }

        Player player = (Player) sender;

        if (args.length != 3) {
            player.sendMessage("§cUso correcto: /battlelootspawnchest <x> <y> <z>");
            return false;
        }

        try {
            double x = Double.parseDouble(args[0]);
            double y = Double.parseDouble(args[1]);
            double z = Double.parseDouble(args[2]);

            Location location = new Location(player.getWorld(), x, y, z);
            Block block = location.getBlock();
            block.setType(org.bukkit.Material.CHEST);

            Chest chest = (Chest) block.getState();
            // Puedes añadir ítems al cofre aquí si es necesario
            chest.update();

            player.sendMessage("§aCofre generado en las coordenadas: " + location);
            return true;

        } catch (NumberFormatException e) {
            player.sendMessage("§cLas coordenadas deben ser números válidos.");
        }

        return false;
    }
}

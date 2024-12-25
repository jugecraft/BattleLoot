package com.battleloot.commands;

import com.battleloot.BattleLoot;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.File;

public class ArenaCommand implements CommandExecutor {

    private BattleLoot plugin;

    public ArenaCommand(BattleLoot plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        // Comando principal: /battlelootarena
        if (cmd.getName().equalsIgnoreCase("battlelootarena") && args.length >= 1) {
            if (args[0].equalsIgnoreCase("create")) {
                return createArena(sender, args);
            } else if (args[0].equalsIgnoreCase("setLobby")) {
                return setLobby(sender);
            } else if (args[0].equalsIgnoreCase("delete")) {
                return deleteArena(sender, args);
            }
        }
        return false;
    }

    // Crear un mundo plano para la arena
    private boolean createArena(CommandSender sender, String[] args) {
        if (args.length != 2) {
            sender.sendMessage("§cUso: /battlelootarena create <nombre>");
            return true;
        }

        String arenaName = args[1];

        // Verifica si ya existe una arena con ese nombre
        if (plugin.getServer().getWorld(arenaName) != null || new File(Bukkit.getWorldContainer(), arenaName).exists()) {
            sender.sendMessage("§cYa existe una arena o un mundo con ese nombre.");
            return true;
        }

        // Crear el nuevo mundo plano
        World world = createFlatWorld(arenaName);

        if (world != null) {
            sender.sendMessage("§aArena '" + arenaName + "' creada exitosamente.");

            // Teletransporta al jugador al mundo recién creado
            if (sender instanceof Player) {
                Player player = (Player) sender;
                player.teleport(new Location(world, 0, 100, 0));
                player.sendMessage("§eHas sido teletransportado a la nueva arena.");
            }
        } else {
            sender.sendMessage("§cHubo un error al crear la arena.");
        }
        return true;
    }

    // Configurar el lobby en la posición actual del jugador
    private boolean setLobby(CommandSender sender) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cEste comando solo puede ser usado por jugadores.");
            return true;
        }

        Player player = (Player) sender;
        Location location = player.getLocation();

        // Guardar la ubicación del lobby
        plugin.getLobbyManager().setLobbyLocation(player.getWorld().getName(), location);

        player.sendMessage("§aLobby configurado exitosamente en tu ubicación actual: " +
                "X: " + location.getBlockX() + ", Y: " + location.getBlockY() + ", Z: " + location.getBlockZ());
        return true;
    }

    // Eliminar una arena
    private boolean deleteArena(CommandSender sender, String[] args) {
        if (args.length != 2) {
            sender.sendMessage("§cUso: /battlelootarena delete <nombre>");
            return true;
        }

        String arenaName = args[1];
        World world = plugin.getServer().getWorld(arenaName);

        if (world == null) {
            sender.sendMessage("§cNo existe ninguna arena con ese nombre.");
            return true;
        }

        // Descargar el mundo y eliminar sus archivos del disco
        plugin.getServer().unloadWorld(world, false); // Descarga el mundo
        File worldFolder = new File(Bukkit.getWorldContainer(), arenaName);

        if (deleteWorldFolder(worldFolder)) {
            sender.sendMessage("§aArena '" + arenaName + "' eliminada exitosamente.");
        } else {
            sender.sendMessage("§cNo se pudo eliminar la arena del sistema de archivos.");
        }
        return true;
    }

    // Crear un mundo plano sin usar Multiverse
    private World createFlatWorld(String worldName) {
        try {
            WorldCreator creator = new WorldCreator(worldName).type(WorldType.FLAT);
            return plugin.getServer().createWorld(creator);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Eliminar físicamente una carpeta de mundo
    private boolean deleteWorldFolder(File folder) {
        if (folder.isDirectory()) {
            for (File file : folder.listFiles()) {
                if (!deleteWorldFolder(file)) {
                    return false;
                }
            }
        }
        return folder.delete();
    }
}

package com.battleloot;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class ChestManager {

    private final BattleLoot plugin;

    public ChestManager(BattleLoot plugin) {
        this.plugin = plugin;
    }

    /**
     * Genera un cofre en una ubicación específica.
     * @param location Ubicación donde generar el cofre.
     * @param player Jugador que invocó la acción (opcional para notificaciones).
     * @return True si se genera el cofre, False si no es posible.
     */
    public boolean spawnChest(Location location, Player player) {
        if (!isValidLocation(location)) {
            if (player != null) {
                player.sendMessage("§c¡No se puede crear el cofre aquí! Verifica que no haya agua, lava o bloqueos.");
            }
            return false;
        }

        if (isChestAtLocation(location)) {
            if (player != null) {
                player.sendMessage("§c¡Ya hay un cofre en esta ubicación! Elige otro lugar.");
            }
            return false;
        }

        // Colocamos un cofre en la ubicación
        location.getBlock().setType(Material.CHEST);
        Chest chest = (Chest) location.getBlock().getState();
        Inventory chestInventory = chest.getInventory();

        // Generar loot
        List<ItemStack> loot = generateLoot();
        for (ItemStack item : loot) {
            chestInventory.addItem(item);
        }

        if (player != null) {
            player.sendMessage("§a¡Cofre creado con éxito en " + location.getBlockX() + ", " +
                    location.getBlockY() + ", " + location.getBlockZ() + "!");
        }

        // Destruir el cofre después de tiempo configurable
        int destructionTime = plugin.getConfig().getInt("loot.destructionTime", 600);
        new BukkitRunnable() {
            @Override
            public void run() {
                Block block = location.getBlock();
                if (block.getType() == Material.CHEST) {
                    block.setType(Material.AIR); // Eliminamos el cofre
                }
            }
        }.runTaskLater(plugin, destructionTime);

        return true;
    }

    /**
     * Verifica si la ubicación es válida (no tiene agua, lava ni otros problemas).
     */
    private boolean isValidLocation(Location location) {
        Block block = location.getBlock();

        // Comprobar que el bloque no sea agua, lava o bloque no sólido.
        return block.isEmpty() && block.getRelative(0, -1, 0).getType().isSolid();
    }

    /**
     * Verifica si ya existe un cofre en esta ubicación.
     */
    private boolean isChestAtLocation(Location location) {
        return location.getBlock().getType() == Material.CHEST;
    }

    /**
     * Genera un loot basado en la configuración del plugin.
     */
    private List<ItemStack> generateLoot() {
        List<ItemStack> loot = new ArrayList<>();
        String lootType = plugin.getConfig().getString("loot.type", "default").toLowerCase();

        try {
            switch (lootType) {
                case "advanced":
                    loot.add(new ItemStack(Material.DIAMOND_SWORD, 1));
                    loot.add(new ItemStack(Material.GOLDEN_APPLE, 5));
                    loot.add(new ItemStack(Material.DIAMOND, 10));
                    loot.add(new ItemStack(Material.ENCHANTED_BOOK, 1));
                    loot.add(new ItemStack(Material.EYE_OF_ENDER, 1));
                    break;

                case "epic":
                    loot.add(new ItemStack(Material.NETHER_STAR, 1));
                    loot.add(new ItemStack(Material.GOLDEN_APPLE, 10));
                    loot.add(new ItemStack(Material.OBSIDIAN, 3));
                    loot.add(new ItemStack(Material.BANNER, 1)); // Estilo épico
                    break;

                case "default":
                default:
                    loot.add(new ItemStack(Material.IRON_SWORD, 1));
                    loot.add(new ItemStack(Material.GOLDEN_APPLE, 3));
                    loot.add(new ItemStack(Material.IRON_INGOT, 5));
                    break;
            }
        } catch (Exception e) {
            Bukkit.getLogger().log(Level.WARNING, "Error generando loot: " + e.getMessage());
        }

        return loot;
    }
}
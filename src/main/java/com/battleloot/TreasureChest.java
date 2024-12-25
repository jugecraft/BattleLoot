package com.battleloot;

import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Location;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.World;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class TreasureChest {

    private BattleLoot plugin;

    // Constructor donde se pasa el plugin
    public TreasureChest(BattleLoot plugin) {
        this.plugin = plugin;
    }

    // Método para generar el cofre
    public void generateTreasureChest(World world, Location loc) {
        // Generar el cofre
        loc.getBlock().setType(Material.CHEST);  // Crear el cofre en la ubicación dada
        Chest chest = (Chest) loc.getBlock().getState(); // Obtener el bloque como un cofre
        Inventory chestInventory = chest.getInventory(); // Obtener el inventario del cofre

        // Crear el loot (botín) para el cofre
        List<ItemStack> loot = generateLoot();

        // Llenar el cofre con los objetos del loot
        for (ItemStack item : loot) {
            chestInventory.addItem(item);
        }

        // Después de unos segundos, desactivar el cofre y eliminarlo
        new BukkitRunnable() {
            @Override
            public void run() {
                // Verificar si el bloque es un cofre antes de eliminarlo
                if (chest.getBlock().getType() == Material.CHEST) {
                    chest.getBlock().setType(Material.AIR);  // Destruir el cofre
                }
            }
        }.runTaskLater(plugin, 200L); // Destruir después de 10 segundos (200 ticks)
    }

    // Método para generar un loot aleatorio
    private List<ItemStack> generateLoot() {
        Random rand = new Random();
        return Arrays.asList(
                new ItemStack(Material.GOLD_SWORD, 1),  // Correcto: Usamos GOLDEN_SWORD
                new ItemStack(Material.DIAMOND, 5),
                new ItemStack(Material.ENCHANTED_BOOK, 1) // Añadir más ítems si es necesario
        );
    }
}
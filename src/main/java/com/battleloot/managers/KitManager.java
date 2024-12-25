package com.battleloot.managers;

import com.battleloot.BattleLoot;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class KitManager {

    private final BattleLoot plugin;

    public KitManager(BattleLoot plugin) {
        this.plugin = plugin;
    }

    // Cargar los kits desde la configuración
    public void loadKits() {
        FileConfiguration config = plugin.getConfig();
        // Aquí podrías cargar los kits desde la configuración (config.yml o algo similar)
        // Ejemplo: Cargar un kit predeterminado que tiene un espada y una poción
    }

    // Método para asignar un kit a un jugador
    public void giveKitToPlayer(Player player, String kitName) {
        // Aquí asocias los objetos o equipos que representan el kit y se los das al jugador
        if (kitName.equalsIgnoreCase("warrior")) {
            player.getInventory().addItem(new ItemStack(Material.DIAMOND_SWORD));
            player.getInventory().addItem(new ItemStack(Material.IRON_HELMET));
            // Agregar más elementos del kit
        } else if (kitName.equalsIgnoreCase("archer")) {
            player.getInventory().addItem(new ItemStack(Material.BOW));
            player.getInventory().addItem(new ItemStack(Material.ARROW, 64));
            // Agregar más elementos del kit
        }
    }

    // Ver si un kit existe
    public boolean isKitExist(String kitName) {
        // Lógica para verificar si un kit existe, posiblemente mirando en config o una lista predefinida
        return true; // Si el kit existe, devolver true
    }
}

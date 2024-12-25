package com.battleloot.events;

import com.battleloot.BattleLoot;
import com.battleloot.ChestManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Material;
import org.bukkit.block.Chest;

public class ChestOpenListener implements Listener {

    private final BattleLoot plugin;

    public ChestOpenListener(BattleLoot plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onChestOpen(PlayerInteractEvent event) {
        // Verificar que la interacción es con un cofre
        if (event.getClickedBlock() != null && event.getClickedBlock().getType() == Material.CHEST) {
            Chest chest = (Chest) event.getClickedBlock().getState();
            Player player = event.getPlayer();

            // Verificar si el cofre fue generado como "Cofre de Tesoro"
            Inventory chestInventory = chest.getInventory();
            if (isTreasureChest(chestInventory)) {
                // Ejecutar la acción (agregar lo que se desea cuando se abre el cofre)
                player.sendMessage("¡Has abierto un cofre de tesoro!");

                // Por ejemplo, darle los objetos al jugador (o cualquier otro comportamiento que quieras)
                for (ItemStack item : chestInventory.getContents()) {
                    if (item != null) {
                        player.getInventory().addItem(item);  // Darle los objetos del cofre
                    }
                }

                // Destruir el cofre después de abrirlo (si lo deseas)
                chest.getBlock().setType(Material.AIR);  // Eliminar el cofre

                // Evitar que se hagan otras acciones sobre el cofre después de que haya sido abierto
                event.setCancelled(true);
            }
        }
    }

    // Este método te puede ayudar a verificar si el cofre contiene el loot y si es un cofre generado
    private boolean isTreasureChest(Inventory chestInventory) {
        // Aquí va la lógica para determinar si el cofre es uno de los generados como cofre de tesoro
        // Por ejemplo, puedes comparar el contenido de los cofres o mantener un marcador específico

        // En este caso solo verificamos si contiene un ítem específico para simplificar
        for (ItemStack item : chestInventory.getContents()) {
            if (item != null && item.getType() == Material.GOLD_SWORD) {
                return true; // Lo consideramos como un "Cofre de Tesoro" si tiene una espada dorada
            }
        }
        return false;
    }
}
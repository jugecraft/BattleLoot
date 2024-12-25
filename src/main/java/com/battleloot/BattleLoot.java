package com.battleloot;

import com.battleloot.commands.*;
import com.battleloot.events.KillListener;
import com.battleloot.events.VictoryListener;
import com.battleloot.managers.LobbyManager; // Importar LobbyManager
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;

public class BattleLoot extends JavaPlugin {

    private static BattleLoot instance;
    private LobbyManager lobbyManager; // Instancia del LobbyManager

    @Override
    public void onEnable() {
        instance = this;
        this.saveDefaultConfig();

        // Inicializar el LobbyManager
        lobbyManager = new LobbyManager();

        // Registrar los eventos con los constructores modificados
        getServer().getPluginManager().registerEvents(new KillListener(this), this);
        getServer().getPluginManager().registerEvents(new VictoryListener(this), this);

        // Registrar los comandos
        registerCommand("battleloothelp", new BattleLootCommand(this));
        registerCommand("battlelootreload", new BattleLootCommand(this));
        registerCommand("battlelootsetLoot", new BattleLootCommand(this));
        registerCommand("battlelootspawnchest", new ChestCommand(this));
        registerCommand("battlelootarena", new ArenaCommand(this));
        registerCommand("battlelootstats", new StatsCommand(this));
        registerCommand("battlelootop", new StatsCommand(this));
        registerCommand("battlelootkit", new KitCommand(this));
        registerCommand("battlelootjoin", new ArenaJoinCommand(this));
        registerCommand("battlelootstart", new ArenaStartCommand(this));

        getLogger().info("BattleLoot habilitado correctamente.");
    }

    @Override
    public void onDisable() {
        getLogger().info("BattleLoot deshabilitado correctamente.");
    }

    private void registerCommand(String name, CommandExecutor executor) {
        if (this.getCommand(name) != null) {
            this.getCommand(name).setExecutor(executor);
        } else {
            getLogger().warning("Comando " + name + " no está configurado en plugin.yml.");
        }
    }

    // Método para acceder a la instancia de LobbyManager
    public LobbyManager getLobbyManager() {
        return lobbyManager;
    }

    public static BattleLoot getInstance() {
        return instance;
    }
}

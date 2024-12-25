package com.battleloot;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

import java.lang.reflect.Field;

public class GameScoreboard {

    private BattleLoot plugin;

    public GameScoreboard(BattleLoot plugin) {
        this.plugin = plugin;
    }

    public void createScoreboard(Player player) {
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard scoreboard = manager.getNewScoreboard();

        // Modificación aquí: solo 2 parámetros, no 3
        Objective objective = scoreboard.registerNewObjective("BattleLoot", "dummy");
        objective.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Servidor: BattleLoot");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        // Datos básicos
        Score spacer1 = objective.getScore(ChatColor.GRAY + "----------------");
        spacer1.setScore(8);

        Score playerName = objective.getScore(ChatColor.YELLOW + "Jugador: " + ChatColor.WHITE + player.getName());
        playerName.setScore(7);

        Score playerRank = objective.getScore(ChatColor.YELLOW + "Rango: " + ChatColor.GREEN + getPlayerRank(player));
        playerRank.setScore(6);

        Score playerLevel = objective.getScore(ChatColor.YELLOW + "Nivel: " + ChatColor.BLUE + getPlayerLevel(player));
        playerLevel.setScore(5);

        Score spacer2 = objective.getScore(ChatColor.GRAY + "----------------");
        spacer2.setScore(4);

        Score playerMoney = objective.getScore(ChatColor.YELLOW + "Dinero: " + ChatColor.GOLD + "$" + getPlayerMoney(player));
        playerMoney.setScore(3);

        Score playerPing = objective.getScore(ChatColor.YELLOW + "Ping: " + ChatColor.RED + getPing(player) + "ms");
        playerPing.setScore(2);

        Score spacer3 = objective.getScore(ChatColor.GRAY + "----------------");
        spacer3.setScore(1);

        Score serverIP = objective.getScore(ChatColor.YELLOW + "IP: " + ChatColor.AQUA + "play.tuservidor.com");
        serverIP.setScore(0);

        // Asignar el scoreboard al jugador
        player.setScoreboard(scoreboard);
    }

    private String getPlayerRank(Player player) {
        // Aquí puedes integrar tu sistema de rangos.
        // Esto es solo un ejemplo:
        return "Miembro";
    }

    private int getPlayerLevel(Player player) {
        // Integra el nivel del jugador desde tu sistema
        // Esto es solo un ejemplo:
        return 1;
    }

    private double getPlayerMoney(Player player) {
        // Integra el sistema de economía.
        // Ejemplo temporal:
        return 1000.0;
    }

    // Obtención de Ping utilizando reflección para versiones < 1.13
    private int getPing(Player player) {
        try {
            // Reflección para obtener el ping en versiones < 1.13
            Object craftPlayer = player.getClass().getMethod("getHandle").invoke(player);
            Field pingField = craftPlayer.getClass().getDeclaredField("ping");
            pingField.setAccessible(true);
            return pingField.getInt(craftPlayer);
        } catch (Exception e) {
            e.printStackTrace();
            return -1; // Si no se puede obtener el ping, devolvemos un valor por defecto
        }
    }
}

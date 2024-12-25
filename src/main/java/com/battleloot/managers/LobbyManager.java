package com.battleloot.managers;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.HashMap;
import java.util.Map;

public class LobbyManager {

    private final Map<String, Location> lobbyLocations = new HashMap<>();

    // Guardar la ubicación de un lobby para un mundo específico
    public void setLobbyLocation(String worldName, Location location) {
        lobbyLocations.put(worldName, location);
    }

    // Obtener la ubicación de un lobby para un mundo
    public Location getLobbyLocation(String worldName) {
        return lobbyLocations.get(worldName);
    }

    // Comprobar si un lobby está configurado
    public boolean hasLobby(String worldName) {
        return lobbyLocations.containsKey(worldName);
    }
}
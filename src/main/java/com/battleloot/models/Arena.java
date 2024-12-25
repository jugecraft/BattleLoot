package com.battleloot.models;

import org.bukkit.Location;

public class Arena {

    private final String name;
    private final Location lobby;
    private final Location spawn;

    public Arena(String name, Location lobby, Location spawn) {
        this.name = name;
        this.lobby = lobby;
        this.spawn = spawn;
    }

    public String getName() {
        return name;
    }

    public Location getLobby() {
        return lobby;
    }

    public Location getSpawn() {
        return spawn;
    }
}
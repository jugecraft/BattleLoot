package com.battleloot.models;

public class PlayerStats {

    private final String playerName;
    private int kills;
    private int deaths;
    private int victories;

    public PlayerStats(String playerName, int kills, int deaths, int victories) {
        this.playerName = playerName;
        this.kills = kills;
        this.deaths = deaths;
        this.victories = victories;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getKills() {
        return kills;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public int getVictories() {
        return victories;
    }

    public void setVictories(int victories) {
        this.victories = victories;
    }
}
package com.battleloot.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerVictoryEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    private final Player winner;

    public PlayerVictoryEvent(Player winner) {
        this.winner = winner;
    }

    public Player getWinner() {
        return winner;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}

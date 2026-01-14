package com.destroystokyo.paper.event.player;

import com.google.gson.JsonObject;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

/**
 * Calls an event in which player stats can be provided. If null, will load from disk, otherwise will use provided data
 */
public class PlayerLoadStatsEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    private final UUID playerId;
    private JsonObject json;

    public PlayerLoadStatsEvent(@NotNull UUID playerId) {
        super(!Bukkit.isPrimaryThread());
        this.playerId = playerId;
    }

    /**
     * Gets the player's unique ID.
     *
     * @return The unique ID
     */
    @NotNull
    public UUID getUniqueId() {
        return playerId;
    }

    @Nullable
    public JsonObject getStatistics() {
        return json;
    }

    public void setStatistics(@NotNull JsonObject json) {
        this.json = json;
    }

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    @NotNull
    public static HandlerList getHandlerList() {
        return handlers;
    }
}

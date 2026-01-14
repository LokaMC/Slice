package io.papermc.paper.event.player;

import org.bson.types.ObjectId;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

/**
 * Called when a player trades with a standalone merchant GUI.
 */
public class PlayerResolveObjectIdEvent extends PlayerEvent {
    private static final HandlerList handlers = new HandlerList();
    private ObjectId objectId;

    public PlayerResolveObjectIdEvent(@NotNull Player player) {
        super(player, !Bukkit.isPrimaryThread());
    }

    @Nullable
    public ObjectId getObjectId() {
        return this.objectId;
    }

    public void setObjectId(@NotNull ObjectId objectId) {
        this.objectId = Objects.requireNonNull(objectId, "ObjectId cannot be null!");
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

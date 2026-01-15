package org.bukkit.event.player;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Called when a player is about to receive an equipment packet about another player
 */
public class PlayerReceiveEquipmentEvent extends PlayerEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private final Entity tracked;
    private boolean cancel;
    private String tag;

    public PlayerReceiveEquipmentEvent(@NotNull final Player player, @NotNull final Entity tracked) {
        super(player);
        this.tracked = tracked;
    }

    /**
     * Gets the tracked entity
     *
     * @return Entity the player is now tracking
     */
    @NotNull
    public Entity getTracked() {
        return tracked;
    }

    @Nullable
    public String getTag() {
        return tag;
    }

    public void setTag(@Nullable String tag) {
        this.tag = tag;
    }

    @Override
    public boolean isCancelled() {
        return cancel;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancel = cancel;
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

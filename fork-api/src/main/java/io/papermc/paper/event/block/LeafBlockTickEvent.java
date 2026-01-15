package io.papermc.paper.event.block;

import org.bukkit.block.Block;
import org.bukkit.event.HandlerList;
import org.bukkit.event.block.BlockEvent;
import org.jetbrains.annotations.NotNull;

/**
 * Called when a leaf block attempts decay, even if it is persistent.
 */
public class LeafBlockTickEvent extends BlockEvent {
    private static final HandlerList handlers = new HandlerList();

    public LeafBlockTickEvent(@NotNull final Block block) {
        super(block);
    }

    @Override
    @NotNull
    public HandlerList getHandlers() {
        return handlers;
    }

    @NotNull
    public static HandlerList getHandlerList() {
        return handlers;
    }
}

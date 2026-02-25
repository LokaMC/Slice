package io.papermc.paper.event.player;

import io.papermc.paper.block.TileStateInventoryHolder;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;

/**
 * Called when a player swaps an item with a slot in a slotted container
 * such as a {@link org.bukkit.block.Shelf}.
 * <p>
 * This event represents a bidirectional exchange: the player's held item
 * goes into the container slot, and the container slot's item goes to the player.
 * <p>
 * For shelves, this occurs during both single-slot swaps (unpowered) and
 * hotbar swaps (powered, across connected shelves).
 */
@NullMarked
public class PlayerSwapItemWithSlottedContainer extends PlayerEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final Block block;
    private final int slot;
    private final ItemStack playerItem;
    private final ItemStack containerItem;

    private boolean cancelled;

    @ApiStatus.Internal
    public PlayerSwapItemWithSlottedContainer(final Player player, final Block block, final int slot, final ItemStack playerItem, final ItemStack containerItem) {
        super(player);
        this.block = block;
        this.slot = slot;
        this.playerItem = playerItem;
        this.containerItem = containerItem;
    }

    /**
     * Gets the slotted container involved in this event.
     * <p>
     * This will be an instance of {@link org.bukkit.block.Shelf}
     * or any other block state that acts as a slotted container supporting swaps.
     *
     * @return the container block state
     */
    public TileStateInventoryHolder getContainer() {
        return (TileStateInventoryHolder) block.getState();
    }

    /**
     * Gets the block of the slotted container involved in this event.
     *
     * @return the block
     */
    public Block getBlock() {
        return block;
    }

    /**
     * Gets the slot index being swapped.
     *
     * @return the slot index
     */
    public int getSlot() {
        return slot;
    }

    /**
     * Gets the item the player is placing into the container slot.
     *
     * @return the item from the player going into the container
     */
    public ItemStack getPlayerItem() {
        return playerItem;
    }

    /**
     * Gets the item currently in the container slot that will go to the player.
     *
     * @return the item from the container going to the player
     */
    public ItemStack getContainerItem() {
        return containerItem;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public void setCancelled(final boolean cancel) {
        this.cancelled = cancel;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }
}
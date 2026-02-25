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
 * Called when a player inserts an item into a slotted container
 * such as a {@link org.bukkit.block.ChiseledBookshelf} or a {@link org.bukkit.block.Shelf}.
 */
@NullMarked
public class PlayerInsertItemIntoSlottedContainer extends PlayerEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final Block block;
    private final int slot;
    private final ItemStack itemStack;

    private boolean cancelled;

    @ApiStatus.Internal
    public PlayerInsertItemIntoSlottedContainer(final Player player, final Block block, final int slot, final ItemStack itemStack) {
        super(player);
        this.block = block;
        this.slot = slot;
        this.itemStack = itemStack;
    }

    /**
     * Gets the slotted container involved in this event.
     * <p>
     * This will be an instance of {@link org.bukkit.block.ChiseledBookshelf},
     * {@link org.bukkit.block.Shelf}, or any other block state that acts as
     * a slotted container.
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
     * Gets the slot index the item is being inserted into.
     *
     * @return the slot index
     */
    public int getSlot() {
        return slot;
    }

    /**
     * Gets the item being inserted into the container.
     *
     * @return the item stack
     */
    public ItemStack getItemStack() {
        return itemStack;
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
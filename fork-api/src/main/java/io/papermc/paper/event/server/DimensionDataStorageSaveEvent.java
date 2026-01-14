package io.papermc.paper.event.server;

import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.server.ServerEvent;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;

import java.io.ByteArrayOutputStream;
import java.nio.file.Path;

/**
 * Called when resources such as datapacks are reloaded (e.g. /minecraft:reload)
 * <p>
 * Intended for use to re-register custom recipes, advancements that may be lost during a reload like this.
 */
@NullMarked
public class DimensionDataStorageSaveEvent extends ServerEvent implements Cancellable {
    public static final HandlerList HANDLER_LIST = new HandlerList();

    private final String id;
    private final ByteArrayOutputStream output;
    private boolean cancelled;
    @ApiStatus.Internal
    public DimensionDataStorageSaveEvent(final String id, final ByteArrayOutputStream output) {
        this.id = id;
        this.output = output;
    }

    public String getId() {
        return id;
    }

    public ByteArrayOutputStream getOutput() {
        return output;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public void setCancelled(final boolean cancel) {
        this.cancelled = cancel;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    public enum Cause {
        COMMAND,
        PLUGIN,
    }
}

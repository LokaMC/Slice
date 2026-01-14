package io.papermc.paper.event.server;

import com.google.gson.JsonElement;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.server.ServerEvent;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;

import java.nio.file.Path;

/**
 * Called when resources such as datapacks are reloaded (e.g. /minecraft:reload)
 * <p>
 * Intended for use to re-register custom recipes, advancements that may be lost during a reload like this.
 */
@NullMarked
public class DimensionDataStorageLoadEvent extends ServerEvent implements Cancellable {
    public static final HandlerList HANDLER_LIST = new HandlerList();

    private final Path path;
    private final JsonElement json;
    private boolean cancelled;
    @ApiStatus.Internal
    public DimensionDataStorageLoadEvent(final Path path, final JsonElement json) {
        this.path = path;
        this.json = json;
    }

    public Path getPath() {
        return path;
    }

    public JsonElement getJson() {
        return json;
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

package io.papermc.paper.event.server;

import org.bukkit.event.HandlerList;
import org.bukkit.event.server.ServerEvent;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;

/**
 * Called when we try to fetch a new index for a newly-created map.
 */
@NullMarked
public class MapGetFreeIndexEvent extends ServerEvent {
    public static final HandlerList HANDLER_LIST = new HandlerList();

    private int index = -1;
    @ApiStatus.Internal
    public MapGetFreeIndexEvent() {
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
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

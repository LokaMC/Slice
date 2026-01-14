package io.papermc.paper.event.server;

import org.bukkit.event.HandlerList;
import org.bukkit.event.server.ServerEvent;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/**
 * Called when we want to load a Map from disk
 */
@NullMarked
public class MapLoadEvent extends ServerEvent {
    public static final HandlerList HANDLER_LIST = new HandlerList();

    private int id;
    private ByteArrayInputStream buf;
    @ApiStatus.Internal
    public MapLoadEvent(int id) {
        this.id = id;
    }

    public int getIndex() {
        return id;
    }

    public void setIndex(int index) {
        this.id = index;
    }

    public @Nullable ByteArrayInputStream getBuf() {
        return buf;
    }

    public void setBuf(ByteArrayInputStream buf) {
        this.buf = buf;
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

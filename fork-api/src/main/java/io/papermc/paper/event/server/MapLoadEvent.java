package io.papermc.paper.event.server;

import org.bukkit.event.HandlerList;
import org.bukkit.event.server.ServerEvent;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/**
 * Called when we want to load a Map from disk.
 * <p>
 * <b>This event is asynchronous.</b> Handlers run on a {@code Dimension-Data-IO-Worker-*}
 * thread (the Paper dimension-data I/O pool), not on the main server thread. Listeners must
 * not call thread-unsafe Bukkit API directly; hop back to the main thread via
 * {@link org.bukkit.Bukkit#getScheduler()} (or the server's main executor) if required.
 * <p>
 * The {@link ByteArrayInputStream} supplied via {@link #setBuf(ByteArrayInputStream)} is read
 * synchronously by the dispatcher immediately after {@link #callEvent()} returns, so setting
 * it from inside the handler is safe.
 */
@NullMarked
public class MapLoadEvent extends ServerEvent {
    public static final HandlerList HANDLER_LIST = new HandlerList();

    private int id;
    private ByteArrayInputStream buf;
    @ApiStatus.Internal
    public MapLoadEvent(int id) {
        super(true);
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

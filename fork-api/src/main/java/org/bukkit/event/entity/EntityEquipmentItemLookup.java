package org.bukkit.event.entity;

import org.bukkit.entity.Entity;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

/**
 * Called when requesting a cached equipment item lookup
 */
public class EntityEquipmentItemLookup extends EntityEvent {
    private static final HandlerList handlers = new HandlerList();
    private final String tag;
    private final EquipmentSlot equipmentSlot;
    private ItemStack itemStack;

    public EntityEquipmentItemLookup(@NotNull final Entity entity, @NotNull String tag, @NotNull EquipmentSlot slot, @NotNull final ItemStack itemStack) {
        super(entity);
        this.tag = tag;
        this.equipmentSlot = slot;
        this.itemStack = itemStack;
    }

    @NotNull
    public ItemStack getItemStack() {
        return itemStack;
    }

    public void setItemStack(@NotNull ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    @NotNull
    public EquipmentSlot getEquipmentSlot() {
        return equipmentSlot;
    }

    @NotNull
    public String getTag() {
        return tag;
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

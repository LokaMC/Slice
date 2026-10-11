package com.n9works.slice;

import java.util.ArrayList;
import java.util.List;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.util.StringUtil;

public final class SmoothInterpolationCommand extends Command {

    private static final String PERMISSION = "slice.command.smoothinterp";
    private static final List<String> TARGETS = List.of("all", "pad", "schedule");
    private static final List<String> STATES = List.of("on", "off");

    public SmoothInterpolationCommand() {
        super("smoothinterp");
        this.description = "Toggle Slice's 26.3 entity interpolation stutter mitigations";
        this.usageMessage = "/smoothinterp [all|pad|schedule] [on|off]";
        this.setPermission(PERMISSION);
    }

    @Override
    public boolean execute(final CommandSender sender, final String label, final String[] args) {
        if (!this.testPermission(sender)) {
            return true;
        }

        if (args.length == 0) {
            sender.sendMessage(Component.text("Smooth interpolation: ", NamedTextColor.GOLD).append(status()));
            return true;
        }

        String target = args.length == 1 ? "all" : args[0].toLowerCase();
        String state = args[args.length - 1].toLowerCase();
        if (args.length > 2 || !TARGETS.contains(target) || !STATES.contains(state)) {
            sender.sendMessage(Component.text("Usage: " + this.usageMessage, NamedTextColor.RED));
            return true;
        }

        boolean enabled = state.equals("on");
        switch (target) {
            case "pad" -> SmoothInterpolation.padSteps = enabled;
            case "schedule" -> SmoothInterpolation.scheduledPlayerPositions = enabled;
            default -> {
                SmoothInterpolation.padSteps = enabled;
                SmoothInterpolation.scheduledPlayerPositions = enabled;
            }
        }

        Component message = Component.text(sender.getName() + " set smooth interpolation " + target + " " + state + ": ", NamedTextColor.GOLD).append(status());
        Bukkit.getConsoleSender().sendMessage(message);
        Bukkit.getOnlinePlayers().stream().filter(p -> p.hasPermission(PERMISSION)).forEach(p -> p.sendMessage(message));
        return true;
    }

    @Override
    public List<String> tabComplete(final CommandSender sender, final String alias, final String[] args) {
        if (args.length == 1) {
            List<String> options = new ArrayList<>(TARGETS);
            options.addAll(STATES);
            return StringUtil.copyPartialMatches(args[0], options, new ArrayList<>());
        }

        if (args.length == 2 && TARGETS.contains(args[0].toLowerCase())) {
            return StringUtil.copyPartialMatches(args[1], STATES, new ArrayList<>());
        }

        return List.of();
    }

    private static Component status() {
        return Component.text("pad=", NamedTextColor.GRAY)
            .append(state(SmoothInterpolation.padSteps))
            .append(Component.text(" schedule=", NamedTextColor.GRAY))
            .append(state(SmoothInterpolation.scheduledPlayerPositions));
    }

    private static Component state(final boolean enabled) {
        return Component.text(enabled ? "on" : "off", enabled ? NamedTextColor.GREEN : NamedTextColor.RED);
    }
}

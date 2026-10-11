package com.n9works.slice;

/**
 * Server-side mitigations for MC-311974 (26.3 stepped entity interpolation stutter).
 * <p>
 * The 26.3 client interpolates each received position step over exactly the step's {@code tickOffset},
 * so the server decides how long remote entities take to reach each position.
 */
public final class SmoothInterpolation {

    /**
     * Pad stepped position paths so their total duration is at least {@code updateInterval + 1} ticks,
     * restoring the one tick of jitter buffer that 26.2's fixed 3-tick interpolation gave players.
     */
    public static volatile boolean padSteps = false;

    /**
     * Only send player positions on their regular update interval. Off-schedule tracker fires (dirty entity data
     * from sprint/health/item-use changes, jump/knockback needsSync) otherwise flush the path early and produce
     * 1-tick steps that the client finishes instantly and then stalls on.
     */
    public static volatile boolean scheduledPlayerPositions = true;

    private SmoothInterpolation() {
    }
}

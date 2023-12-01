package net.most.survivaltimemod.util;

public class ShardOptions {
    private WeightMinMax loopOptions;
    private WeightMinMax fluxOptions;
    private WeightMinMax epochOptions;
    private WeightMinMax temporaOptions;
    private WeightMinMax chronaOptions;

    public ShardOptions(WeightMinMax loopOptions, WeightMinMax fluxOptions, WeightMinMax epochOptions,
                        WeightMinMax temporaOptions, WeightMinMax chronaOptions) {
        this.loopOptions = loopOptions;
        this.fluxOptions = fluxOptions;
        this.epochOptions = epochOptions;
        this.temporaOptions = temporaOptions;
        this.chronaOptions = chronaOptions;
    }
    public WeightMinMax getLoopOptions() {
        return loopOptions;
    }
    public WeightMinMax getFluxOptions() {
        return fluxOptions;
    }
    public WeightMinMax getEpochOptions() {
        return epochOptions;
    }
    public WeightMinMax getTemporaOptions() {
        return temporaOptions;
    }
    public WeightMinMax getChronaOptions() {
        return chronaOptions;
    }
    public void setLoopOptions(WeightMinMax loopOptions) {
        this.loopOptions = loopOptions;
    }
    public void setFluxOptions(WeightMinMax fluxOptions) {
        this.fluxOptions = fluxOptions;
    }
    public void setEpochOptions(WeightMinMax epochOptions) {
        this.epochOptions = epochOptions;
    }
    public void setTemporaOptions(WeightMinMax temporaOptions) {
        this.temporaOptions = temporaOptions;
    }
    public void setChronaOptions(WeightMinMax chronaOptions) {
        this.chronaOptions = chronaOptions;
    }


}

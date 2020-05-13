package lab14;

import lab14lib.Generator;

public class StrangeBitwiseGenerator implements Generator {
    private int period;
    private int state;

    public StrangeBitwiseGenerator(int period) {
        state = 0;
        this.period = period;
    }

    public double next() {
        state = (state + 1);
        int weirdState = state & (state >> 7) % period;
        return normalize(weirdState % period);
    }
    private double normalize (int n) {
        return (double) n / (period - 1) * 2 - 1;
    }
}

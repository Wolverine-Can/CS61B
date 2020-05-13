package lab14;

import lab14lib.Generator;

public class AcceleratingSawToothGenerator implements Generator {
    private int period;
    private double factor;
    private int state;

    public AcceleratingSawToothGenerator(int period, double factor) {
        state = 0;
        this.factor = factor;
        this.period = period;
    }

    public double next() {
        state = (state + 1);
        double next = normalize(state % period);
        if (next == -1) {
            state = 0;
            period *= factor;
        }
        return next;
    }

    private double normalize (int n) {
        return (double) n / (period - 1) * 2 - 1;
    }
}

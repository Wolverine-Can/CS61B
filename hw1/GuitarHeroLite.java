/** A client that uses the synthesizer package to replicate a plucked guitar string sound */
public class GuitarHeroLite {
    private static final double CONCERT_A = 880.00;
    private static final double CONCERT_B = 987.77;
    private static final double CONCERT_C = 523.25;
    private static final double CONCERT_D = 587.33;
    private static final double CONCERT_E = 659.25;
    private static final double CONCERT_F = 698.46;
    private static final double CONCERT_G = 793.99;

    public static void main(String[] args) {
        /* create two guitar strings, for concert A and C */
        synthesizer.GuitarString stringA = new synthesizer.GuitarString(CONCERT_A);
        synthesizer.GuitarString stringB = new synthesizer.GuitarString(CONCERT_B);
        synthesizer.GuitarString stringC = new synthesizer.GuitarString(CONCERT_C);
        synthesizer.GuitarString stringD = new synthesizer.GuitarString(CONCERT_D);
        synthesizer.GuitarString stringE = new synthesizer.GuitarString(CONCERT_E);
        synthesizer.GuitarString stringF = new synthesizer.GuitarString(CONCERT_F);
        synthesizer.GuitarString stringG = new synthesizer.GuitarString(CONCERT_G);

        while (true) {

            /* check if the user has typed a key; if so, process it */
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                switch (key) {
                    case'1': stringC.pluck(); break;
                    case'2': stringD.pluck(); break;
                    case'3': stringE.pluck(); break;
                    case'4': stringF.pluck(); break;
                    case'5': stringG.pluck(); break;
                    case'6': stringA.pluck(); break;
                    case'7': stringB.pluck(); break;
                    default: System.out.println("wrong key");
                }
            }

        /* compute the superposition of samples */
            double sample = stringA.sample() + stringB.sample()
                    + stringC.sample() + stringD.sample()
                    + stringE.sample() + stringF.sample()
                    + stringG.sample();

        /* play the sample on standard audio */
            StdAudio.play(sample);

        /* advance the simulation of each guitar string by one step */
            stringA.tic();
            stringB.tic();
            stringC.tic();
            stringD.tic();
            stringE.tic();
            stringF.tic();
            stringG.tic();
        }
    }
}


public class DurandKerner {

    public static double[] durandKerner(Poly p) {
        Complex accuracy = new Complex(Math.pow(10, -16), Math.pow(10, -16));
        Complex start = new Complex(.4, .9);

        // init roots -> starting points
        Complex[] roots = new Complex[p.degree];
        for (int i = 0; i < p.degree; i++) {
            roots[i] = Complex.power(start, new Complex(i, 0));
        }

        boolean running = true;
        while (running) {
            Complex[] newRoots = new Complex[roots.length];

            // calc for every root
            for (int i = 0; i < roots.length; i++) {
                Complex numerator = p.solve(roots[i]);
                Complex denominator = Complex.multiply(roots, i);
                Complex fraction = Complex.divide(numerator, denominator);
                Complex root = Complex.minus(roots[i], fraction);
                newRoots[i] = root;
            }

            // check if change is less than eqsilon
            for (int i = 0; i < newRoots.length; i++) {
                if (Complex.isLessThan(Complex.minus(newRoots[i], roots[i]), accuracy)) {
                    running = false;
                } else {
                    running = true;
                }
            }

            roots = newRoots;
        }
    }

    public static void main(String[] args) {
        
    }
}

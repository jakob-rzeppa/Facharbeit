public class DurandKernerMethod {
    private static final double ACCURACY = 0.001;
    private static final double ACCURACY_NUMBERS_AFTER_DECIMALPOINT = 4;

    // nC -> numerator coefficients; dP -> denominator points
    private static Complex calc(Complex start, Polynomial f, Complex[] roots, int valIndex) {
        System.out.println("start: " + start.round(ACCURACY_NUMBERS_AFTER_DECIMALPOINT));
        // get the value of the numerator
        Complex nV = f.solve(start);
        System.out.println("numerator: " + nV.round(ACCURACY_NUMBERS_AFTER_DECIMALPOINT));

        // get the value of the denominator
        Complex[] dFs = new Complex[roots.length];
        for (int i = 0; i < dFs.length; i++) {
            if (i == valIndex) { continue; }
            dFs[i] = Complex.minus(start, roots[i]);
        }
        Complex dV = Complex.multiply(dFs);
        System.out.println("denominator: " + dV.round(ACCURACY_NUMBERS_AFTER_DECIMALPOINT));


        // create the fraction
        Complex fr = Complex.divide(nV, dV);
        System.out.println("fraction: " + fr.round(ACCURACY_NUMBERS_AFTER_DECIMALPOINT));

        // return the new value
        Complex result = Complex.minus(start, fr);
        System.out.println("result: " + result.round(ACCURACY_NUMBERS_AFTER_DECIMALPOINT));
        System.out.println();
        return result;
    }

    private static Complex[] startingPoints(Polynomial f) {
        double[] a = f.getCoefficients();
        int n = f.getDegree();
        Complex[] staringPoints = new Complex[f.getDegree()];

        // radius
        //double r = Math.abs(n * a[0] / 2 * a[1]) + Math.abs(a[n - 1] / 2 * n * a[n]);
        double r = Math.pow(Math.abs(a[0]/a[n]), 1 / n);
        // theta
        double theta = 2 * Math.PI / n;
        // offset
        double c = Math.PI / 2 * n;

        // calculate the starting points around a circle in the complex plane (x = a + bi)
        for (int k = 0; k < staringPoints.length; k++) {
            double real = r * Math.cos(k * theta + c);
            double imag = r * Math.sin(k * theta + c);
            staringPoints[k] = new Complex(real, imag);
        }

        return staringPoints;
    }

    private static Complex[] recursion(Complex[] roots, Polynomial f) {
        Complex[] newRoots = new Complex[roots.length];

        System.out.println();
        System.out.println();
        System.out.println("-----");
        for (Complex complex : roots) {
            System.out.println(complex.round(ACCURACY_NUMBERS_AFTER_DECIMALPOINT));
        }
        System.out.println("-----");
        System.out.println();

        // perform the calculation for every point
        for (int i = 0; i < roots.length; i++) {
            newRoots[i] = calc(roots[i], f, roots, i);
        }

        // check if the |change| is smaller then the ACCURACY
        double realChange = Math.abs(newRoots[0].real - roots[0].real);
        double imagChange = Math.abs(newRoots[0].imag - roots[0].imag);
        if (realChange < ACCURACY && imagChange < ACCURACY) {
            return newRoots;
        }

        return recursion(newRoots, f);
    }

    public static Complex[] durandKerner(Polynomial f) {
        // perform the recursion with the starting values
        return recursion(startingPoints(f), f);
    }

    public static void main(String[] args) {
        Polynomial p = new Polynomial(12,-2,-4);
        Complex[] roots = startingPoints(p);
        //System.out.println(calc(roots[1], p, roots, 1));
        roots = recursion(roots, p);
        for (Complex complex : roots) {
            System.out.println(complex.round(ACCURACY_NUMBERS_AFTER_DECIMALPOINT));
        }
    }
}
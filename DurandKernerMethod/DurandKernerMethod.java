public class DurandKernerMethod {
    private static final double ACCURACY = 0.001;

    // nC -> numerator coefficients; dP -> denominator points
    private static Complex calc(Complex lastVal, Polynomial f, Complex[] roots, int valIndex) {
        // get the value of the numerator
        Complex nV = f.solve(lastVal);
        

        // get the value of the denominator
        Complex[] dFs = new Complex[roots.length];
        for (int i = 0; i < dFs.length; i++) {
            if (i == valIndex) { continue; }
            dFs[i] = Complex.minus(lastVal, roots[i]);
        }
        Complex dV = Complex.multiply(dFs);


        // create the fraction
        Complex fr = Complex.divide(nV, dV);

        // return the new value
        return Complex.minus(lastVal, fr);
    }

    private static Complex[] startingPoints(Polynomial f) {
        double[] a = f.getCoefficients();
        int n = f.getDegree();
        Complex[] staringPoints = new Complex[f.getDegree()];

        // radius
        double r = Math.abs(n * a[0] / 2 * a[1]) + Math.abs(a[n - 1] / 2 * n * a[n]);
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

        // perform the calculation for every point
        for (int i = 0; i < roots.length; i++) {
            newRoots[i] = calc(roots[i], f, roots, i);
        }

        // check if the |change| is smaller then the ACCURACY
        if (f.solve(newRoots[0]).abs().real < ACCURACY && f.solve(newRoots[0]).abs().imag < ACCURACY) {
            return newRoots;
        }

        // else recursion
        return recursion(newRoots, f);
    }

    public static Complex[] durandKerner(Polynomial f) {
        // perform the recursion with the starting values
        return recursion(startingPoints(f), f);
    }

    public static void main(String[] args) {
        Polynomial p = new Polynomial(5d,2d,-4d);
        /*Complex[] roots = new Complex[2];
        roots[0] = new Complex(-1);
        roots[1] = new Complex(1); //2.5
        roots = recursion(roots, p);
        for (Complex r : roots) {
            System.out.println(r);
        }*/
        Complex[] cs = durandKerner(p);
        for (Complex c : cs) {
            System.out.println(c);
        }
    }
}
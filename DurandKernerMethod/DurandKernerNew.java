public class DurandKernerNew {
    public static final int MAX = 10;

    private static boolean changeSmallerThen(double maxChange, Complex[] arr, Complex[] oldArr) {
        for (int i = 0; i < arr.length; i++) {
            if (Math.abs(arr[i].real - oldArr[i].real) > maxChange || Math.abs(arr[i].imag - oldArr[i].imag) > maxChange) {
                return false;
            }
        }
        return true;
    }

    /**
     * Durand-Kerner-Method
     * @param f function
     * @param r array of roots to iterate on
     * @param index estimated root index
    **/
    private static Complex durandkerner(Polynomial f, Complex[] roots, int index) {
        Complex numerator = f.solve(roots[index]);
        //System.out.println("numerator: " + numerator.round());

        Complex denominator = new Complex(1);
        for (int i = 0; i < roots.length; i++) {
            if (i != index) {
                Complex term = Complex.minus(roots[index], roots[i]);
                //System.out.println("t: " + term.round());

                denominator = Complex.multiply(denominator, term);
                //System.out.println(denominator.round());
            }
        }
        //System.out.println("denominator: " + denominator.round());

        Complex fraction = Complex.divide(numerator, denominator);
        //System.out.println("fraction: " + fraction.round());

        Complex result = Complex.minus(roots[index], fraction);
        //System.out.println("result: " + result.round());
        //System.out.println();

        return result;
    }

    private static Complex[] startingPoints(Polynomial f) {
        Complex[] roots = new Complex[f.getDegree()];

        // init startig circle
        int n = f.getDegree();
        double r = Math.pow(Math.abs(f.getCoefficients()[0]/f.getCoefficients()[n]), 1/n);  // nthRoot(|a_0/a_n|)
        double theta = 2 * Math.PI / n;                                                     // evenly space circle
        double offset = theta / n + 1;                                                      // can't start with real number

        // find Points
        System.out.println("starting values: ");
        for (int k = 0; k < n; k++) {
            roots[k] = new Complex(r * Math.cos(k * theta + offset), r * Math.sin(k * theta + offset));

            System.out.println(roots[k].round(4));
        }
        System.out.println();

        return roots;
    }

    /**
     * Polynomial Solver
     * @return array of complex roots
    **/
    private static Complex[] solve(Polynomial f) {
        Complex[] roots = startingPoints(f);

        // begin root finding
        Complex[] oldRoots;
        int i = 0;
        do {
            // print
            System.out.print(i + ": [b=");
            for (int j = 0; j < roots.length - 1; j++) {
                String s = (j==0) ? "a=" : "c=";
                System.out.print(roots[j].round(4) + ", " + s);
            }
            System.out.print(roots[roots.length - 1].round(4) + "]");
            System.out.println();
            // print
            
            oldRoots = roots.clone();
            for (int j = 0; j < oldRoots.length; j++) {
                roots[j] = durandkerner(f, oldRoots, j);
            }
        } while (!changeSmallerThen(0.001, roots, oldRoots) && ++i < MAX);
        // print
        System.out.print(i + ": [");
        for (int j = 0; j < roots.length - 1; j++) {
            System.out.print(roots[j].round(4) + ", ");
        }
        System.out.print(roots[roots.length - 1].round(4) + "]");
        System.out.println();
        System.out.println();
        System.out.println("Iterations: " + i);
        if (i >= MAX) { System.err.println("max limit triggered!"); }
        System.out.println();
        // print 
        return roots;
    }

    public static void main(String[] args) {
        Polynomial f = new Polynomial(-5,1,-1,+5);
        Complex[] c = new Complex[3];
        c[0] = new Complex(-0.1,0.9);
        c[1] = new Complex(-0.8,-0.6);
        c[2] = new Complex(0.9,-0.4);
        for (Complex complex : solve(f)) {
            System.out.println(complex.round());
        }
    }
}
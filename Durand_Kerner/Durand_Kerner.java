package Durand_Kerner;

public class Durand_Kerner {
    private static final double ACCURACY = 0.001;

    // nC -> numerator coefficients; dP -> denominator points
    private static Complex calc(Complex lastVal, Polynomial f, Complex[] dP) {
        // get the value of the numerator
        Complex nV = f.solve(lastVal);
        

        // get the value of the denominator
        Complex[] dFs = new Complex[dP.length];
        for (int i = 0; i < dFs.length; i++) {
            dFs[i] = Complex.minus(lastVal, dP[i]);
        }
        Complex dV = Complex.multiply(dFs);


        // create the fraction
        Complex fr = Complex.devide(nV, dV);

        // return the new value
        return Complex.minus(lastVal, fr);
    }

    private static Complex[] startingVals(Polynomial f) {

        return new Complex[f.degree];
    }

    private static Complex[] recursion(Complex[] roots, Polynomial f) {
        // perform the calculation for every point

        // check if the |change| is smaller then the ACCURACY

        // else recursion
        return recursion(roots, f);
    }

    public static Complex[] durandKerner(Polynomial f) {
        // perform the recursion with the starting values
        return recursion(startingVals(f), f);
    }



    public static void main(String[] args) {
        Complex c = new Complex(3, 2);
        Complex d = c;
        System.out.println(c.toString());
        System.out.println(d.toString());
        d.minus(new Complex(1, 1));
        System.out.println(c.toString());
        System.out.println(d.toString());
    }

}
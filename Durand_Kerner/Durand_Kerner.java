package Durand_Kerner;

public class Durand_Kerner {
    // Degree: 2 <=> r1 = r0 - f(r0) / ((r0 - s0)(r0 - t0))

    private double[] fC; // coefficients of the function f
    private int degree;

    private Complex[] roots;

    public Durand_Kerner(double... coefficients) {
        fC = coefficients;
        this.degree = fC.length;
        findRoots();
    }

    public void findRoots() {
        roots = new Complex[degree]; // R,S,T,...
        findStartPoints();
        durandKerner();
    }

    private void findStartPoints() {


        
    }

    private void durandKerner() {

        // do things


        // check for change lower then ...
        


        // else go on

        durandKerner();
    }
}

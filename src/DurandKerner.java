import java.util.ArrayList;
import java.util.List;

public class DurandKerner {
    /**
     * @param roots are the "roots" of the last iteration
     * @param index is the index of the current iteration
     * @return the more accurate root
     */
    private static Complex calc(Poly p, Complex[] roots, int index) {
        // numerator
        Complex numerator = p.solve(roots[index]);

        // denominator
        List<Complex> factors = new ArrayList<Complex>();
        for (int i = 0; i < roots.length; i++) {
            if (i == index) {
                continue;
            }
            factors.add(Complex.minus(roots[index], roots[i]));
        }
        // multiply all the linear factors
        Complex denominator = new Complex(1, 0);
        for (int i = 0; i < factors.size(); i++) {
            denominator = Complex.multiply(denominator, factors.get(i));
        }

        // correction term
        Complex correctionTerm = Complex.divide(numerator, denominator);

        return Complex.minus(roots[index], correctionTerm);
    }

    private static Complex[] startingPoints(Poly p) {
        // helpvars to not divide by zero
        double t1 = (p.coefficients[1] == 0) ? 1 : p.coefficients[1];
        double t2 = (p.coefficients[p.degree] == 0) ? 1: p.coefficients[p.degree];

        double radius = Math.abs((p.degree * p.coefficients[0]) / (2 * t1)) + Math.abs(p.coefficients[p.degree - 1] / (2 * p.degree * t2));
        double theta = 2 * Math.PI / p.degree;
        double offset = Math.PI / (2 * p.degree);
        Complex[] roots = new Complex[p.degree];

        for (int i = 0; i < p.degree; i++) {
            //roots[i] = new Complex(radius * Math.cos(i * theta + offset), radius * Math.sin(i * theta + offset));
            roots[i] = new Complex(radius * Math.cos(i * theta + offset), 0);
            System.out.println(roots[i].round(4));
        }
        return roots;
    }

    private static boolean isAccurateEnough(Complex[] newRoots, Complex[] oldRoots, double accuracy) {
        boolean result = false;
        for (int i = 0; i < newRoots.length; i++) {
            if (Complex.minus(newRoots[i], oldRoots[i]).abs() < accuracy) {
                result = true;
            } else {
                result = false;
            }
        }
        return result;
    }

    public static Complex[] durandKerner(Poly p) {
		// for the Durand-Kerner-Method a_n has to be equal to one -> devide every a by a_n (the polynomial won't be the same, but the roots stay the same)
        p.normalise();

        // The accuracy of the Durand-Kerner-Method
        double accuracy = Math.pow(10, -4);
        double maxIterations = 1000;

        System.out.println("function: " + p.toString());

        // init roots -> starting points
        Complex[] roots = startingPoints(p);

        boolean running = true;
        int ctr = 0;
        while (running) {
            // init the array for the new roots
            Complex[] newRoots = new Complex[roots.length];

            System.out.println("iteration: " + ++ctr);

            // calc for every root
            for (int i = 0; i < roots.length; i++) {
                newRoots[i] = calc(p, roots, i);
            }

            for (int i = 0; i < roots.length; i++) {
                System.out.println("i = " + i + ": " + newRoots[i].round(4));
            }
            System.out.println();

            // check if change is less than eqsilon
            if (isAccurateEnough(newRoots, roots, accuracy) || ctr >= maxIterations) {
                running = false;
            }

            roots = newRoots;
        }

        // Probe
        for (Complex r : roots) {
            System.out.println("Probe: f(" + r.round(4) + ") = " + p.solve(r).round(4));
        }

        return roots;
    }

    public static void main(String[] args) {
        Poly p = new Poly(2,2,-4,1);
        durandKerner(p);
    }
}

import java.util.ArrayList;
import java.util.List;

/**
 * Weierstrass
 *
 * This class implements the Weierstrass-Iteration for finding roots of a polynomial.
 * The Weierstrass-Iteration is an iterative numerical algorithm that refines initial
 * guesses for the roots of a polynomial by repeatedly applying a correction term to
 * each guess until convergence is reached.
 */
public class Weierstrass {
    /**
     * @param p      The polynomial for which roots are being calculated.
     * @param roots  The current approximation of roots.
     * @param index  The index of the root being updated in the iteration.
     * @return       The more accurate root.
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

    /**
     * Generate initial guesses (starting points) for the roots based on the
     * properties of the polynomial.
     *
     * @param p The polynomial for which roots are being calculated.
     * @return  Array of initial guesses for the roots.
     */
    private static Complex[] startingPoints(Poly p) {
        // check so that there is no devision by zero
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

    /**
     * Check if the roots have converged to a sufficiently accurate solution.
     *
     * @param newRoots   The updated roots in the current iteration.
     * @param oldRoots   The roots from the previous iteration.
     * @param accuracy   The desired accuracy for convergence.
     * @return           True if roots have converged; otherwise, false.
     */
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

    /**
     * Find the roots of the polynomial using the Weierstrass-Iteration.
     *
     * @param p The polynomial for which roots are being calculated.
     * @return  Array of roots.
     */
    public static Complex[] weierstrass(Poly p) {
		// for the Weierstrass-Iteration a_n has to be equal to one -> devide every a by a_n (the polynomial won't be the same, but the roots stay the same)
        p.normalise();

        // The accuracy of the Weierstrass-Iteration
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

    //TODO
    public static void main(String[] args) {
        Poly p = new Poly(2,2,-4,1);
        weierstrass(p);
    }
}
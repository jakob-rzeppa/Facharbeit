import java.util.ArrayList;
import java.util.List;

public class DurandKerner {

    private static Complex denominator(Complex[] roots, int index) {
        List<Complex> factors = new ArrayList<Complex>();
        for (int i = 0; i < roots.length; i++) {
            if (i == index) {
                continue;
            }
            factors.add(Complex.minus(roots[index], roots[i]));
        }
        return Complex.multiply(factors);
    }

    public static Complex[] durandKerner(Poly p) {
        Complex accuracy = new Complex(Math.pow(10, -8), Math.pow(10, -8));
        Complex start = new Complex(.4, .9);

        // init roots -> starting points
        Complex[] roots = new Complex[p.degree];
        for (int i = 0; i < p.degree; i++) {
            roots[i] = Complex.power(start, i);
        }

        boolean running = true;
        int ctr = 0;
        while (running) {
            Complex[] newRoots = new Complex[roots.length];

            System.out.println("iteration: " + ctr++);

            // calc for every root
            for (int i = 0; i < roots.length; i++) {
                Complex numerator = p.solve(roots[i]);
                Complex denominator = denominator(roots, i);
                Complex fraction = Complex.divide(numerator, denominator);
                Complex root = Complex.minus(roots[i], fraction);
                System.out.println("i = " + i + ": " + root);
                newRoots[i] = root;
            }
            System.out.println();

            // check if change is less than eqsilon
            for (int i = 0; i < newRoots.length; i++) {
                if (Complex.isLessThan(Complex.minus(newRoots[i], roots[i]).abs(), accuracy)) {
                    running = false;
                } else {
                    running = true;
                }
            }

            roots = newRoots;
        }

        // Probe
        for (Complex r : roots) {
            System.out.println("Probe: f(" + r + ") = " + p.solve(r));
        }
        return roots;
    }

    public static void main(String[] args) {
        Poly p = new Poly(3,2,1,-23,24,7,34,-342,345);
        durandKerner(p);
    }
}

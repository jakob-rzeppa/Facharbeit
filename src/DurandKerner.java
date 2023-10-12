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
        Complex accuracy = new Complex(Math.pow(10, -4), Math.pow(10, -4));

        System.out.println("function: " + p.toString());

        // init roots -> starting points
        // helpvariables to not divide by zero
        double t1 = (p.coefficients[1] == 0) ? 1 : p.coefficients[1];
        double t2 = (p.coefficients[p.degree] == 0) ? 1: p.coefficients[p.degree];
        double radius = Math.abs((p.degree * p.coefficients[0]) / (2 * t1)) + Math.abs(p.coefficients[p.degree - 1] / (2 * p.degree * t2));
        double theta = 2 * Math.PI / p.degree;
        double offset = Math.PI / (2 * p.degree);
        Complex[] roots = new Complex[p.degree];

        System.out.println("start:");

        for (int i = 0; i < p.degree; i++) {
            roots[i] = new Complex(radius * Math.cos(i * theta + offset), radius * Math.sin(i * theta + offset));
            System.out.println(roots[i].round());
        }

        System.out.println();

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
                System.out.println("i = " + i + ": " + root.round());
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
            System.out.println("Probe: f(" + r.round() + ") = " + p.solve(r).round());
        }
        return roots;
    }

    public static void main(String[] args) {
        Poly p = new Poly(-4,3,-2,4,1);
        durandKerner(p);
    }
}

package NewMethod;

public class RationalFunction {
    private Polynomial numerator;
    private Polynomial denominator;

    public RationalFunction(Polynomial numerator, Polynomial denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public double solve(double x) {
        return numerator.solve(x) / denominator.solve(x);
    }

    public void setNumerator(Polynomial numerator) {
        this.numerator = numerator;
    }
    public void setDenominator(Polynomial denominator) {
        this.denominator = denominator;
    }

    public Polynomial getNumerator() { return numerator; }
    public Polynomial getDenominator() { return denominator; }

    public void multiply(Polynomial factor) {
        this.numerator.multiply(factor);
    }
    public void divide(Polynomial divisor) {
        this.denominator.multiply(divisor);
    }

    public String toString() {
        return "(" + numerator.toString() + ") / (" + denominator.toString() + ")";
    }


    public static void main(String[] args) {
        Polynomial p = new Polynomial(3d,5d,0d,-2d);
        Polynomial q = new Polynomial(1d, 5d);
        RationalFunction f = new RationalFunction(p, q);

        System.out.println(f.getNumerator().toString());
        System.out.println(f.getDenominator().toString());

        System.out.println(f.getNumerator().solve(0));
        System.out.println(f.solve(0));
        System.out.println(f.toString());
    }
}

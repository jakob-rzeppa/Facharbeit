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
    public RationalFunction derive() {
        Polynomial derivativeNumeratorFactor1 = new Polynomial(denominator);
        derivativeNumeratorFactor1.multiply(numerator.derive());

        Polynomial derivativeNumeratorFactor2 = new Polynomial(numerator);
        derivativeNumeratorFactor2.multiply(denominator.derive());

        Polynomial derivativeNumerator = new Polynomial(derivativeNumeratorFactor1);
        derivativeNumerator.subtract(derivativeNumeratorFactor2);

        Polynomial derivativeDenominator = new Polynomial(denominator);
        derivativeDenominator.multiply(denominator);

        RationalFunction derivative = new RationalFunction(derivativeNumerator, derivativeDenominator);

        return derivative;
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
        Polynomial p = new Polynomial(5d,8d,-2d);
        Polynomial q = new Polynomial(1d, -2d);
        RationalFunction f = new RationalFunction(p, q);

        System.out.println(f.toString());
        System.out.println(f.derive().toString());
    }
}
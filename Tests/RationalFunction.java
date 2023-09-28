import java.util.List;
import java.util.ArrayList;

class RationalFunction extends Function {
    private Polynomial numerator;
    private Polynomial denominator;

    public RationalFunction(Polynomial polynomial, Polynomial denominator) {
        this.numerator = polynomial;
        this.denominator = denominator;
    }

    public double solve(double d) {
        try {
            return numerator.solve(d) / denominator.solve(d);
        } catch (ArithmeticException e) {
            throw e;
        }
        
    }

    public Function derive() {
        Polynomial numerator;
        Polynomial denominator;
    }

    //Form: (x - d)
    public void multiply(double d) {
        
    }

    public Function devide(Function denominator) {
        return this;
    }

    public String toString() {
        return "";
    }
}
import java.util.List;
import java.util.ArrayList;

abstract class Function {
    public abstract double solve(double d);
    public abstract Function derive();
    public abstract String toString();
    public abstract Function devide(Function denominator);
    public abstract void multiply(double d); //Form: (x - d)
}
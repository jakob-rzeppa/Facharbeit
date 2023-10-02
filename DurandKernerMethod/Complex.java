public class Complex {
    public final double real;
    public final double imag;

    public Complex(double real, double imag) {
        this.real = real;
        this.imag = imag;
    }

    public Complex(double real) {
        this.real = real;
        this.imag = 0;
    }

    public static Complex plus(Complex a, Complex b) {
        if (a == null) { return b; }
        if (b == null) { return a; }
        double real = a.real + b.real;
        double imag = a.imag + b.imag;
        return new Complex(real, imag);
    }

    public static Complex minus(Complex a, Complex b) {
        if (a == null) { return b; }
        if (b == null) { return a; }
        double real = a.real - b.real;
        double imag = a.imag - b.imag;
        return new Complex(real, imag);
    }

    public static Complex multiply(Complex a, Complex b) {
        if (a == null) { return b; }
        if (b == null) { return a; }
        double real = a.real * b.real - a.imag * b.imag;
        double imag = a.real * b.imag + a.imag * b.real;
        return new Complex(real, imag);
    }

    public static Complex divide(Complex a, Complex b) {
        if (a == null) { return b; }
        if (b == null) { return a; }
        double real = (a.real * b.real + a.imag * b.imag) / (b.real * b.real + b.imag * b.imag);
        double imag = (a.imag * b.real - a.real * b.imag) / (b.real * b.real + b.imag * b.imag);
        return new Complex(real, imag);
    }

    public static Complex multiply(Complex[] factors) {
        if (factors.length == 1) { return factors[0]; }
        Complex product = Complex.multiply(factors[0], factors[1]);
        for (int i = 2; i < factors.length; i++) {
            product = Complex.multiply(product, factors[i]);
        }
        return product;
    }

    public static Complex power(Complex c, int exponent) {
        if (exponent == 0) {
            return new Complex(1);
        }
        Complex product = c;
        for (int i = 2; i <= exponent; i++) {
            product = Complex.multiply(product, c);
        }
        return product;
    }

    public Complex abs() {
        double real = (this.real >= 0) ? this.real : -this.real;
        double imag = (this.imag >= 0) ? this.imag : -this.imag;
        return new Complex(real, imag);
    }

    public static boolean inRangeFromZero(Complex p, double range) {
        if ((p.real > -0.001 && p.real < 0.001) && (p.imag > -0.001 && p.imag < 0.001)) {
            return true;
        }  
        return false;
    }

    @Override
    public String toString() {
        if (imag == 0) return real + "";
        if (real == 0) return imag + "i";
        if (imag <  0) return real + " - " + (-imag) + "i";
        return real + " + " + imag + "i";
    }

    public static void main(String[] args) {
        
    }
}
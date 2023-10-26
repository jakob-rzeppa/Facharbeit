import java.util.List;

class Complex {
    private double re; // real part
    private double im; // imaginary part

    Complex(double re, double im) {
        this.re = re;
        this.im = im;
    }

    // static methods
    public static Complex plus(Complex a, Complex b) {
        double re = a.re + b.re;
        double im = a.im + b.im;
        return new Complex(re, im);
    }

    public static Complex minus(Complex a, Complex b) {
        double re = a.re - b.re;
        double im = a.im - b.im;
        return new Complex(re, im);
    }

    public static Complex multiply(Complex a, Complex b) {
        double re = a.re * b.re - a.im * b.im;
        double im = a.re * b.im + a.im * b.re;
        return new Complex(re, im);
    }

    public static Complex divide(Complex a, Complex b) {
        double re = (a.re * b.re + a.im * b.im) / (b.re * b.re + b.im * b.im);
        double im = (a.im * b.re - a.re * b.im) / (b.re * b.re + b.im * b.im);
        return new Complex(re, im);
    }

    public static Complex power(Complex c, int exponent) {
        if (exponent < 0) { throw new RuntimeException("Negative exponentiation isn't implemented"); }
        if (exponent == 0) {
            return new Complex(1, 0);
        }
        Complex product = c;
        for (int i = 2; i <= exponent; i++) {
            product = Complex.multiply(product, c);
        }
        return product;
    }

    public double abs() {
        return Math.sqrt(Math.pow(re, 2) + Math.pow(im, 2));
    }

    public Complex round() {
        // shove the numbers after the decimal point in front of the decimal point and cut of the rest
        double factor = Math.pow(10, 4);
		double r1 = Math.round(this.re * factor);
        double i1 = Math.round(this.im * factor);

        // shove the numbers after the decimal point back to where they belong
        double r2 = (double) r1 / factor;
        double i2 = (double) i1 / factor;
        
		return new Complex(r2, i2);
    }

    @Override
    public String toString() {
        if (im == 0) return re + "";
        if (re == 0) return im + "i";
        if (im <  0) return re + " - " + (-im) + "i";
        return re + " + " + im + "i";
    }
}
import java.util.List;

class Complex {
    double re;
    double im;

    Complex(double re, double im) {
        this.re = re;
        this.im = im;
    }

    public static Complex plus(Complex a, Complex b) {
        if (a == null) { return b; }
        if (b == null) { return a; }
        double re = a.re + b.re;
        double im = a.im + b.im;
        return new Complex(re, im);
    }

    public static Complex minus(Complex a, Complex b) {
        if (a == null) { return b; }
        if (b == null) { return a; }
        double re = a.re - b.re;
        double im = a.im - b.im;
        return new Complex(re, im);
    }

    public static Complex multiply(Complex a, Complex b) {
        if (a == null) { return b; }
        if (b == null) { return a; }
        double re = a.re * b.re - a.im * b.im;
        double im = a.re * b.im + a.im * b.re;
        return new Complex(re, im);
    }

    public static Complex divide(Complex a, Complex b) {
        if (a == null) { return b; }
        if (b == null) { return a; }
        double re = (a.re * b.re + a.im * b.im) / (b.re * b.re + b.im * b.im);
        double im = (a.im * b.re - a.re * b.im) / (b.re * b.re + b.im * b.im);
        return new Complex(re, im);
    }

    public static Complex power(Complex c, int exponent) {
        if (exponent == 0) {
            return new Complex(1, 0);
        }
        Complex product = c;
        for (int i = 2; i <= exponent; i++) {
            product = Complex.multiply(product, c);
        }
        return product;
    }

    public static Complex multiply(List<Complex> factors) {
        Complex result = new Complex(1, 0);
        for (int i = 0; i < factors.size(); i++) {
            result = Complex.multiply(result, factors.get(i));
        }
        return result;
    }

    public Complex abs() {
        double re = (this.re >= 0) ? this.re : -this.re;
        double im = (this.im >= 0) ? this.im : -this.im;
        return new Complex(re, im);
    }

    public static boolean isLessThan(Complex a, Complex b) {
        if (a.re > b.re) {
            return false;
        }
        if (a.im > b.im) {
            return false;
        }
        return true;
    }

    public Complex round(double placesAfterDecimalPoint) {
        // shove the numbers after the decimal point in front of the decimal point and cut of the rest
        double factor = Math.pow(10, placesAfterDecimalPoint);
		double r1 = Math.round(this.re * factor);
        double i1 = Math.round(this.im * factor);

        // shove the numbers after the decimal point back to where they belong
        double r2 = (double) r1 / factor;
        double i2 = (double) i1 / factor;
        
		return new Complex(r2, i2);
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

    public static void main(String[] args) {
        Complex[] c = new Complex[3];
        c[0] = new Complex(1, 0);
        c[1] = new Complex(-2.34, 0.67);
        c[2] = new Complex(4.8, -2);
        System.out.println(Complex.power(new Complex(2,4), 4));
    }
}
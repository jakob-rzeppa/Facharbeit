/**
 * The Complex class represents complex numbers and provides basic operations
 * such as addition, subtraction, multiplication, division, and exponentiation.
 */
class Complex {
    private double real; // real part
    private double imaginary; // imaginary part

    /**
     * Constructor for initializing a complex number with a real and an imaginary part.
     *
     * @param real The real part of the complex number.
     * @param im The imaginary part of the complex number.
     */
    Complex(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    /**
     * Calculates the absolute value of the complex number.
     *
     * @return The absolute value of the complex number.
     */
    public double abs() {
        return Math.sqrt(Math.pow(real, 2) + Math.pow(imaginary, 2));
    }

    /**
     * Rounds the real and imaginary parts of the complex number to the specified number
     * of decimal places.
     *
     * @param accuracyDecimalPlaces The number of decimal places for rounding.
     * @return                      A new complex number with rounded real and imaginary parts.
     */
    public Complex round(int accuracyDecimalPlaces) {
        // shove the numbers after the decimal point in front of the decimal point and cut of the rest
        double factor = Math.pow(10, accuracyDecimalPlaces);
		double r1 = Math.round(this.real * factor);
        double i1 = Math.round(this.imaginary * factor);

        // shove the numbers after the decimal point back to where they belong
        double r2 = (double) r1 / factor;
        double i2 = (double) i1 / factor;
        
		return new Complex(r2, i2);
    }

    /**
     * Returns a string representation of the complex number.
     *
     * @return The string representation of the complex number.
     */
    @Override
    public String toString() {
        if (imaginary == 0) return real + "";
        if (real == 0) return imaginary + "i";
        if (imaginary <  0) return real + " - " + (-imaginary) + "i";
        return real + " + " + imaginary + "i";
    }

    // ---- Static methods for complex number operations ----

    public static Complex plus(Complex a, Complex b) {
        double re = a.real + b.real;
        double im = a.imaginary + b.imaginary;
        return new Complex(re, im);
    }

    public static Complex minus(Complex a, Complex b) {
        double re = a.real - b.real;
        double im = a.imaginary - b.imaginary;
        return new Complex(re, im);
    }

    public static Complex multiply(Complex a, Complex b) {
        double re = a.real * b.real - a.imaginary * b.imaginary;
        double im = a.real * b.imaginary + a.imaginary * b.real;
        return new Complex(re, im);
    }

    public static Complex divide(Complex a, Complex b) throws ArithmeticException {
        double re = (a.real * b.real + a.imaginary * b.imaginary) / (b.real * b.real + b.imaginary * b.imaginary);
        double im = (a.imaginary * b.real - a.real * b.imaginary) / (b.real * b.real + b.imaginary * b.imaginary);
        return new Complex(re, im);
    }

    /**
     * Raises a complex number to a power.
     *
     * @param c                     The complex number.
     * @param exponent              The exponent (a non-negative integer).
     * @return                      The result of the exponentiation.
     * @throws ArithmeticException  if a negative exponent is passed.
     */
    public static Complex power(Complex c, int exponent) throws ArithmeticException {
        if (exponent < 0) { throw new ArithmeticException("Negative exponentiation isn't implemented"); }
        if (exponent == 0) {
            return new Complex(1, 0);
        }
        Complex product = c;
        for (int i = 2; i <= exponent; i++) {
            product = Complex.multiply(product, c);
        }
        return product;
    }
}
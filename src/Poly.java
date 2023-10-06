class Poly {
    public double[] coefficients;
    public int degree;

    /**
     * takes a function of the form f(x) = a_n * x^(n) + a_n-1 * x^(n-1) + ... + a_1 * x + a_0 as input
     * @param coefficients contains all coefficients a of the function with the index of which representing n
     * there may be no coefficients that equals zero after the last non zero coefficient
     */
    Poly(double... coefficients) {
        this.coefficients = coefficients;
        this.degree = coefficients.length - 1;

        // for the Durand-Kerner-Method a_n has to be equal to one -> devide every a by a_n 
        for (int i = 0; i < this.coefficients.length; i++) {
            this.coefficients[i] = this.coefficients[i] / this.coefficients[degree];
        }
    }

    //TODO documentation
    public Complex solve(Complex val) {
        Complex result = new Complex(0, 0);
        Complex x = new Complex(1, 0);
        for (double c : coefficients) {
            result = Complex.plus(result, Complex.multiply(x, new Complex(c, 0)));
            x = Complex.multiply(x, val);
        }
        return result;
    }
}
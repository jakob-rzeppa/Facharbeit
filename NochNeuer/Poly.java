class Poly {
    public double[] coefficients;
    public int degree;

    Poly(double... coefficients) {
        this.coefficients = coefficients;
        this.degree = coefficients.length - 1;
        makeNthCoefficientOne();
    }

    private void makeNthCoefficientOne() {
        if (coefficients[degree] == 1) { return; }
        for (int i = 0; i < coefficients.length; i++) {
            coefficients[i] = coefficients[i] / coefficients[degree];
            //System.out.println(coefficients[degree]);
        }
    }

    public Complex solve(Complex val) {
        Complex result = new Complex(0, 0);
        Complex x = new Complex(1, 0);
        for (double c : coefficients) {
            result = Complex.plus(result, Complex.multiply(x, new Complex(c, 0)));
            x = Complex.multiply(x, val);
        }
        return result;
    }

    public static void main(String[] args) {
        Poly p = new Poly(3,2,4);
    }
}
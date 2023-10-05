class Poly {
    public double[] coefficients;
    public int degree;

    Poly(double... coefficients) {
        this.coefficients = coefficients;
        this.degree = coefficients.length - 1;
    }

    Complex solve(Complex val) {
        Complex result = new Complex(0, 0);
        Complex x = new Complex(1, 0);
        for (double c : coefficients) {
            result = Complex.plus(result, Complex.multiply(x, new Complex(c, 0)));
            x = Complex.multiply(x, val);
        }
        return result;
    }

    public static void main(String[] args) {
        Poly p = new Poly(3,4);
        System.out.println(p.solve(new Complex(1,2)));
    }
}
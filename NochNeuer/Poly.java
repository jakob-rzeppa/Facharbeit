class Poly {
    public double[] coefficients;
    public int degree;

    Poly(double... coefficients) {
        this.coefficients = coefficients;
        this.degree = coefficients.length - 1;
    }

    double solve(double val) {
        double result = 0;
        double x = 1;
        for (double c : coefficients) {
            result += x * c;
            x *= val;
        }
        return result;
    }

    public static void main(String[] args) {
        Poly p = new Poly(3,4);
        System.out.println(p.solve(1));
    }
}
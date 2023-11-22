/**
 * The "Poly" class represents a polynomial with real coefficients and provides methods
 * for normalization, solving for complex values and generating a string representation.
 */
class Poly {
	/**
     * Array containing coefficients of the polynomial, where the index represents the power of x.
     * For example, coefficients[2] represents the coefficient of x^2.
     */
    public double[] coefficients;

	/**
     * The degree of the polynomial, which is the highest power of x with a non-zero coefficient.
     */
    public int degree;

    /**
     * Constructs a polynomial with the given coefficients.
     */
    Poly(double... coefficients) {
        this.coefficients = coefficients;
        this.degree = coefficients.length - 1;
    }

	/**
     * Normalizes the polynomial by dividing each coefficient by the leading coefficient.
	 * The roots of the polynomial won't change.
     */
	public void normalise() {
		for (int i = 0; i < this.coefficients.length; i++) {
            this.coefficients[i] = this.coefficients[i] / this.coefficients[degree];
        }
	}

	/**
     * Solves the polynomial for a given complex value.
     *
     * @param val 	The complex value for which the polynomial is evaluated.
     * @return 		The complex result of evaluating the polynomial for the given value.
     */
    public Complex solve(Complex val) {
		Complex result = new Complex(0, 0);
        for (int i = 0; i < coefficients.length; i++) {
			Complex c = Complex.power(val, i);
			Complex monomial = Complex.multiply(new Complex(coefficients[i], 0), c);
			result = Complex.plus(result, monomial);
		}
        return result;
    }

	/**
     * Generates a string representation of the polynomial.
     *
     * @return A string representing the polynomial in a human-readable form.
     */
    @Override
	public String toString() {
		if (coefficients.length == 0) {
			return "";
		}

		StringBuilder out = new StringBuilder();

		for (int i = coefficients.length - 1; i >= 0; i--) {
			if (coefficients[i] == 0) {
				continue;
			}

			if (out.length() > 0) {
				out.append(coefficients[i] >= 0 ? " + " : " - ");
			}

			double absCoefficient = Math.abs(coefficients[i]);
			if (i > 1) {
				out.append(absCoefficient).append("x^(").append(i).append(")");
			} else if (i == 1) {
				out.append(absCoefficient).append("x");
			} else {
				out.append(absCoefficient);
			}
		}

		return out.toString();
	}
}
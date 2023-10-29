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
    }

	public void normalise() {
		// for the Durand-Kerner-Method a_n has to be equal to one -> devide every a by a_n (the polynomial won't be the same, but the roots stay the same)
		for (int i = 0; i < this.coefficients.length; i++) {
            this.coefficients[i] = this.coefficients[i] / this.coefficients[degree];
        }
	}

    public Complex solve(Complex val) {
		Complex result = new Complex(0, 0);
        for (int i = 0; i < coefficients.length; i++) {
			Complex c = Complex.power(val, i);
			Complex monomial = Complex.multiply(new Complex(coefficients[i], 0), c);
			result = Complex.plus(result, monomial);
		}
        return result;
    }

    @Override
    public String toString() {
        //edge cases
		if (coefficients.length == 0) {
			return "";
		} if (coefficients.length == 1) {
			return coefficients[0] + "";
		} if (coefficients.length == 2) {
			if (coefficients[0] >= 0) {
				return coefficients[1] + "x +" + coefficients[0];
			} else {
				return coefficients[1] + "x " + coefficients[0];
			}
		}

        String out = "";
		for (int i = coefficients.length - 1; i > 1; i--) {
			if (coefficients[i] == 0) {
				continue;
			}
			if (coefficients[i] >= 0) {
				out += "+" + coefficients[i] + "x^(" + i + ") ";
			} else {
				out += coefficients[i] + "x^(" + i + ") ";
			}
		}
		if (coefficients[1] >= 0) {
			if (coefficients[0] >= 0) {
				out += "+" + coefficients[1] + "x" + " +" + coefficients[0];
			} else {
				out += "+" + coefficients[1] + "x" + " " + coefficients[0];
			}
		} else {
			if (coefficients[0] >= 0) {
				out += coefficients[1] + "x" + " +" + coefficients[0];
			} else {
				out += coefficients[1] + "x" + " " + coefficients[0];
			}
		}

		return out;
    }
}
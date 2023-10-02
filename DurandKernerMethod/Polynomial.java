class Polynomial{
    private double[] coefficients;
    private int degree;

    public Polynomial(double... coefficients) {
        this.coefficients = new double[coefficients.length];
        int i = this.coefficients.length - 1;
        for (double d : coefficients) {
            this.coefficients[i--] = d;
        }
        this.degree = this.coefficients.length - 1;
    }

    public Complex solve(Complex x) {
        if (this.degree == 0) {
            return new Complex(this.coefficients[0]);
        }

        Complex result = new Complex(0);
        for (int i = 0; i < coefficients.length; i++) {
            Complex termProduct = Complex.multiply(new Complex(coefficients[i]), Complex.power(x, i));
            result = Complex.plus(result, termProduct);
        }
        return result;
    }

    public double[] getCoefficients() { return coefficients; }
    public int getDegree() { return degree; }

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

    public static void main(String[] args) {
        
    }
}

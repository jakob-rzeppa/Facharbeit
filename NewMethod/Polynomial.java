import java.util.List;
import java.util.ArrayList;

class Polynomial {
	private List<Double> coefficients;
	private double brokenDividend = 0;
	private double brokenDivisorSubtrahend;
	private int degree;
	
	public Polynomial(double brokenDividend, double brokenDivisorSubtrahend, boolean writingOrder, double... coefficients) {
		this.brokenDividend = brokenDividend;
		this.brokenDivisorSubtrahend = brokenDivisorSubtrahend;

		this.coefficients = new ArrayList<>();
		if (writingOrder) {
			for (int i = coefficients.length - 1; i >= 0; i--) {
				this.coefficients.add(coefficients[i]);
			}
		} else if (!writingOrder) {
			for (int i = 0; i < coefficients.length; i++) {
				this.coefficients.add(coefficients[i]);
			}
		}
		
		this.degree = this.coefficients.size() - 1;
	}

	public Polynomial(double brokenDividend, double brokenDivisorSubtrahend, List<Double> coefficients) {
		this.brokenDividend = brokenDividend;
		this.brokenDivisorSubtrahend = brokenDivisorSubtrahend;
		this.coefficients = coefficients;
		this.degree = this.coefficients.size() - 1;
	}
	
	public double solve(double val) {
        double result = 0;
        for (int i = 0; i < coefficients.size(); i++) {
            result += coefficients.get(i) * Math.pow(val, i);
        }
        return result;
	}

    public Polynomial derive() {
		double[] newCoefficients = new double[coefficients.size() - 1];
        for (int i = 1; i < coefficients.size(); i++) {
			newCoefficients[i - 1] = Main.round(coefficients.get(i) * i, 4);
        }
        return new Polynomial(0, 0, false, newCoefficients);
    }

	public Polynomial devide(double subtrahend) {
		double[] coefficientsQuotient = new double[coefficients.size() - 1];

		// aC * x^(aE) + bC * x^(bE)
		double a = 0;
		double b = 0;

		for (int i = coefficients.size() - 1; i > 0; i--) {
			System.out.println("I: " + i);
			a = coefficients.get(i) - b;
			b = a * (-subtrahend);
			System.out.println("a: " + a + " b: " + b);

			coefficientsQuotient[i - 1] = a;
		}

		if (b != 0) {
			return new Polynomial(b, subtrahend, false, coefficientsQuotient);
		}

		return new Polynomial(0, 0, false, coefficientsQuotient);
	}

    // ---- get methods ----
	public List<Double> getCoefficients() {
		return coefficients;
	}
	public int getDegree() {
		return degree;
	}

    // ---- override methods ----
    public String toString() {
		//edge cases
		if (coefficients.size() == 0) {
			return "";
		} if (coefficients.size() == 1) {
			return coefficients.get(0) + "";
		} if (coefficients.size() == 2) {
			if (coefficients.get(0) >= 0) {
				return coefficients.get(1) + "x + " + coefficients.get(0);
			} else {
				return coefficients.get(1) + "x" + coefficients.get(0);
			}
		}
		
        String out = "";
		for (int i = coefficients.size() - 1; i > 1; i--) {
			if (coefficients.get(i) == 0) {
				continue;
			}
			if (coefficients.get(i) >= 0) {
				out += "+" + coefficients.get(i) + "x^(" + i + ") ";
			} else {
				out += coefficients.get(i) + "x^(" + i + ") ";
			}
		}
		if (coefficients.get(1) >= 0) {
			if (coefficients.get(0) >= 0) {
				out += "+" + coefficients.get(1) + "x" + " +" + coefficients.get(0);
			} else {
				out += "+" + coefficients.get(1) + "x" + " " + coefficients.get(0);
			}
		} else {
			if (coefficients.get(0) >= 0) {
				out += coefficients.get(1) + "x" + " +" + coefficients.get(0);
			} else {
				out += coefficients.get(1) + "x" + " " + coefficients.get(0);
			}
		}

		if (brokenDividend != 0) {
			if (brokenDivisorSubtrahend >= 0) {
				out += " +(" + brokenDividend + ")/(x+" + brokenDivisorSubtrahend + ")";
			} else {
				out += " +(" + brokenDividend + ")/(x" + brokenDivisorSubtrahend + ")";
			}
			
		}

		return out;
    }

   // ---- debug ----
    public static void main(String[] args) {
		Polynomial p = new Polynomial(0, 0, true, 5d,3d,-12d);
		System.out.println(p.toString());
		System.out.println(p.devide(4).toString());
    
	}
}
import java.util.List;
import java.util.ArrayList;

class Polynomial extends Function {
	private List<Double> coefficients;
	private int degree;
	
	public Polynomial(boolean writingOrder, double... coefficients) {
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

	public Polynomial(List<Double> coefficients) {
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

    /*public Polynomial derive() {
		double[] newCoefficients = new double[coefficients.size() - 1];
        for (int i = 1; i < coefficients.size(); i++) {
			newCoefficients[i - 1] = Main.round(coefficients.get(i) * i, 4);
        }
        return new Polynomial(false, newCoefficients);
    }*/

	/*public Function devide(Function denomiantor) {
		return new RationalFunction(this, denomiantor);
	}*/

	//Form: (x - d)
	public void multiply(double d) {
		d = -d;

		coefficients.add(0, 0d);

		for (int i = 1; i < coefficients.size(); i++) {
			coefficients.set(i - 1, coefficients.get(i) * d + coefficients.get(i - 1));
		}
	}

	public void multiply(Polynomial p) {
		
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

		return out;
    }

	// ---- helper functions ----
	/*public static Polynomial fromZeroPointForm(double... values) {
		for (int i = 0; i < values.length - 1; i++) {
			
		}
	}*/


   // ---- debug ----
    public static void main(String[] args) {
		Polynomial p = new Polynomial(true, 5d,2d,3d);
		System.out.println(p.toString());
		System.out.println();
		p.multiply(2);
		System.out.println(p.toString());
	}
}
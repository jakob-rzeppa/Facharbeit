import java.util.List;
import java.util.ArrayList;

class Polynomial2 {
	private List<Double> coefficients;
	private int degree;
	
	public Polynomial(List<Double> coefficients) {
		this.coefficients = coefficients;
		this.degree = coefficients.size() - 1;
	}
	
	public double solve(double val) {
        double result = 0;
        for (int i = 0; i < coefficients.size(); i++) {
            result += coefficients.get(i) * Math.pow(val, i);
        }
        return result;
	}

    public Polynomial derive() {
		List<Double> newCoefficients = new ArrayList<Double>();
        for (int i = 1; i < coefficients.size(); i++) {
			newCoefficients.add(Main.round(coefficients.get(i) * i, 4));
        }
        return new Polynomial(newCoefficients);
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
			return coefficients.get(1) + "x^(1)+(" + coefficients.get(0) + ")";
		}
		
        String out = "";
		for (int i = coefficients.size() - 1; i > 1; i--) {
			if (coefficients.get(i) == 0) {
				continue;
			}
			out += "+(" + coefficients.get(i) + "x^(" + i + "))";
		}
		out += "+(" + coefficients.get(1) + "x)" + "+(" + coefficients.get(0) + ")";
		return out;
    }

   // ---- debug ----
    public static void main(String[] args) {
		List<Double> c = new ArrayList<Double>();
		c.add(-23.5d);
		c.add(12d);
		c.add(-45.2d);
		c.add(-124d);
		c.add(23.1d);
		Polynomial p = new Polynomial(c);
		System.out.println(p.derive().toString());
		System.out.println(p.derive().getDegree());
		System.out.println(p.derive().solve(43.5d));
    
	}
}
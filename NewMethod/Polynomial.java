package NewMethod;

import java.util.List;
import java.util.ArrayList;

class Polynomial{
    private List<Double> coefficients;
    private int degree;

    public Polynomial(List<Double> coefficients) {
        this.coefficients = coefficients;
        this.degree = this.coefficients.size() - 1;
    }
    public Polynomial(double... coefficients) {
        this.coefficients = new ArrayList<>();
        for (double coefficient : coefficients) {
            this.coefficients.add(0, coefficient);
        }
        this.degree = this.coefficients.size() - 1;
    }

    public Polynomial() {
        this.coefficients = new ArrayList<Double>();
        this.coefficients.add(0d);
        this.degree = 0;
    }

    public double solve(double x) {
        if (this.degree == 0) {
            return this.coefficients.get(0);
        }

        double result = 0;
        for (int i = 0; i < coefficients.size(); i++) {
            result += coefficients.get(i) * Math.pow(x, i);
        }
        return result;
    }
    public Polynomial derive() {
        if (this.degree == 0) {
            throw new RuntimeException("cant derive polinomial of zeroth degree");
        }

        List<Double> newCoefficients = new ArrayList<Double>();
        for (int i = 1; i < coefficients.size(); i++) {
			newCoefficients.add(coefficients.get(i) * i);
        }
        return new Polynomial(newCoefficients);
    }

    public void add(Polynomial addend) {
        List<Double> addendCoefficients = addend.getCoefficients();
        for (int i = 0; i < addendCoefficients.size(); i++) {
            try {
                coefficients.set(i, coefficients.get(i) + addendCoefficients.get(i));
            } catch (IndexOutOfBoundsException e) {
                coefficients.add(addendCoefficients.get(i));
            }
        }
    }
    public void subtract(Polynomial subtrahend) {
        List<Double> subtrahendCoefficients = subtrahend.getCoefficients();
        for (int i = 0; i < subtrahendCoefficients.size(); i++) {
            try {
                coefficients.set(i, coefficients.get(i) - subtrahendCoefficients.get(i));
            } catch (IndexOutOfBoundsException e) {
                coefficients.add(-subtrahendCoefficients.get(i));
            }
        }
    }
    public void multiply(Polynomial factor) {
        List<Double> factorCoefficients = factor.getCoefficients();
        List<Double> productCoefficients = new ArrayList<Double>();
  
        // init productCoefficients
        for (int i = 0; i < coefficients.size() + factorCoefficients.size() - 1; i++) 
        {
            productCoefficients.add(0d);
        }

        // multiply polynomials term by term
        for (int i = 0; i < coefficients.size(); i++) 
        {
            for (int j = 0; j < factorCoefficients.size(); j++) 
            {
                productCoefficients.set(i + j, productCoefficients.get(i + j) + (coefficients.get(i) * factorCoefficients.get(j)));
            }
        }
        
        this.coefficients = productCoefficients;
    }

    public List<Double> getCoefficients() { return coefficients; }
    public int getDegree() { return degree; }

    @Override
    public String toString() {
        //edge cases
		if (coefficients.size() == 0) {
			return "";
		} if (coefficients.size() == 1) {
			return coefficients.get(0) + "";
		} if (coefficients.size() == 2) {
			if (coefficients.get(0) >= 0) {
				return coefficients.get(1) + "x +" + coefficients.get(0);
			} else {
				return coefficients.get(1) + "x " + coefficients.get(0);
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

    public static void main(String[] args) {
        Polynomial p = new Polynomial(3d,2d);
        Polynomial q = new Polynomial(-1d);

        System.out.println(p.toString());
        System.out.println(q.toString());
        p.multiply(q);
        System.out.println(p.toString());
    }
}

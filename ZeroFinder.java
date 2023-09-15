import java.util.ArrayList;
import java.util.List;

class ZeroFinder {
	public static List<Double> findZeros(Polynomial function) {
		Polynomial firstDerivative = function.derive();
		Polynomial secDerivative = firstDerivative.derive();

		//recursive end
		if (function.getDegree() == 1) {
			List<Double> zeros = new ArrayList<Double>();
			zeros.add(-(function.getCoefficients().get(0) / function.getCoefficients().get(1)));

			//print
			System.out.println("Degree: " + function.getDegree());
			System.out.println("Function: " + function.toString());
			System.out.println("Zero: " + zeros.get(0));
			System.out.println();
			return zeros;
		}
		
		//recursive
		List<Double> zerosDerivative = findZeros(firstDerivative);
		
		//edge cases
		if (zerosDerivative.size() == 0) {
			List<Double> zeros = new ArrayList<Double>();
			zeros.add(newtonmethod(function, firstDerivative, 0d));

			//print
			System.out.println("Degree: " + function.getDegree());
			System.out.println("Function: " + function.toString());
			System.out.println("Zero: " + zeros.get(0));
			System.out.println();
			return zeros;
		}
		
		
		for (int i = 0; i < zerosDerivative.size(); i++) {
			if (function.solve(zerosDerivative.get(i)) == 0) {
				zerosDerivative.remove(i);
			}
		}
		
		List<List<Double>> areas = findAreas(function, secDerivative, zerosDerivative);
		
		List<Double> zeros = new ArrayList<Double>();
		for (int i = 0; i < areas.size(); i++) {
			zeros.add(bisectormethod(function, areas.get(i)));
		}
		
		//edge cases
		//TODO probleme
		if ((function.solve(zerosDerivative.get(0)) > 0 && function.getDegree() < 0) ||
			(function.solve(zerosDerivative.get(0)) < 0 && function.getDegree() > 0)) {
			zeros.add(newtonmethod(function, firstDerivative, zerosDerivative.get(0) - 1));
		}
		if ((function.solve(zerosDerivative.get(zerosDerivative.size() - 1)) > 0 && function.getDegree() < 0) ||
			(function.solve(zerosDerivative.get(zerosDerivative.size() - 1)) < 0 && function.getDegree() > 0)) {
			zeros.add(newtonmethod(function, firstDerivative, (double) zerosDerivative.get(zerosDerivative.size() - 1) + 1));
		}
		
		//sort
		zeros.sort(null);
		
		//print
		System.out.println("Degree: " + function.getDegree());
		System.out.println("Function: " + function.toString());
		if (zeros.size() == 0) {
			System.out.println("No Zeros");
		}
		for (int i = 0; i < zeros.size(); i++) {
			System.out.println("Zero " + i + ": " + zeros.get(i));
		}
		System.out.println();

		return zeros;
	}
	
	private static List<List<Double>> findAreas(Polynomial function, Polynomial secDerivative, List<Double> zerosDerivative) {
		List<List<Double>> areas = new ArrayList<List<Double>>();
		
		for (int i = 0; i < zerosDerivative.size() - 1; i++) {
			if (!(function.solve(zerosDerivative.get(i)) > 0 && 0 > function.solve(zerosDerivative.get(i + 1)) || 
				function.solve(zerosDerivative.get(i)) < 0 && 0 < function.solve(zerosDerivative.get(i + 1)))) {
				continue;
			}
			if (secDerivative.solve(zerosDerivative.get(i)) == 0 || secDerivative.solve(zerosDerivative.get(i + 1)) == 0) {
				continue;
			}
			System.out.print("Area: " + zerosDerivative.get(i) + ", " + zerosDerivative.get(i + 1) + "\n");
			areas.add(new ArrayList<Double>());
			areas.get(areas.size() - 1).add(zerosDerivative.get(i));
			areas.get(areas.size() - 1).add(zerosDerivative.get(i + 1));
		}
		
		return areas;
	}
	
	private static double bisectormethod(Polynomial function, List<Double> area) {
		double a = area.get(0);
		double b = area.get(1);
		
		if (function.solve(area.get(0)) > -0.000001 && function.solve(area.get(1)) < 0.000001) {
			return a;
		}
		
		return bisectormethodRuntime(function, a, b).get(0);
	}
	
	private static List<Double> bisectormethodRuntime(Polynomial function, double a, double b) {
		double c = (a + b) / 2;
		
		double aV = function.solve(a);
		double bV = function.solve(b);
		double cV = function.solve(c);
		
		List<Double> newArea = new ArrayList<Double>();
		
		if (aV > -0.000001 && bV < 0.000001) {
			newArea.add(a); newArea.add(a);
			return newArea;
		}
		
		if ((aV > 0 && cV < 0) || (aV < 0 && cV > 0)) {
			return bisectormethodRuntime(function, a, c);
		}
		if ((cV > 0 && bV < 0) || (cV < 0 && bV > 0)) {
			return bisectormethodRuntime(function, c, b);
		}
		throw new RuntimeException("??? bisectormethod");
	}
	
	private static double newtonmethod(Polynomial function, Polynomial firstDerivative, Double value) {
		//recursive end
		if (function.solve(value) < 0.0001d && function.solve(value) > -0.0001d) {
			return value;
		}
		
		value = value - function.solve(value) / firstDerivative.solve(value);

		//recursive
		return newtonmethod(function, firstDerivative, value);
	}

	public static void main(String[] args) {
		List<Double> c = new ArrayList<Double>();
		c.add(-5d);
		c.add(0d);
		c.add(2d);
		c.add(4d);
		c.add(-1d);
		Polynomial p = new Polynomial(c);
		findZeros(p);
	}
}
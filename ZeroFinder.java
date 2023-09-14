import java.util.ArrayList;
import java.util.List;

class ZeroFinder {
	private static List<Double> findZeros(Polynomial function, Polynomial firstDerivative, Polynomial secDerivative) {

		//recursive end
		if (function.getDegree() == 1) {
			List<Double> zeros = new ArrayList<Double>();
			zeros.add(-(function.getZeroMonomialCoefficient() / function.getFirstMonomialCoefficient()));
			return zeros;
		}
		
		//recursive
		List<Double> zerosDerivative = findZeros(firstDerivative, secDerivative, secDerivative.derive());
		
		//edge cases
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
		if ((function.solve(zerosDerivative.get(0)) > 0 && function.getMonomials().get(0).getCoefficient() < 0) ||
			(function.solve(zerosDerivative.get(0)) < 0 && function.getMonomials().get(0).getCoefficient() > 0)) {
			zeros.add(newtonmethod(function, firstDerivative, zerosDerivative.get(0) - 1));
		}
		if ((function.solve(zerosDerivative.get(zerosDerivative.size() - 1)) > 0 && function.getMonomials().get(0).getCoefficient() < 0) ||
			(function.solve(zerosDerivative.get(zerosDerivative.size() - 1)) < 0 && function.getMonomials().get(0).getCoefficient() > 0)) {
			zeros.add(newtonmethod(function, firstDerivative, zerosDerivative.get(zerosDerivative.size() - 1) + 1));
		}
		
		//TODO sort
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
		double zero = value;
		
		//round
		if (function.solve(zero) < 0.000001 && function.solve(zero) > -0.000001) {
			return zero;
		}
		
		zero -= function.solve(zero) / firstDerivative.solve(zero);
		
		//recursive
		return newtonmethod(function, firstDerivative, zero);
	}
}
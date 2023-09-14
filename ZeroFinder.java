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
		List<Double> zeros = bisectormethod(function, areas);
		
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
	
	private static List<Double> bisectormethod(Polynomial function, List<List<Double>> areas) {
		List<Double> zeros = new ArrayList<Double>();
		
		//edge cases
		
		
		//recursive
		return zeros;
	}
	
	private static Double newtonmethod(Polynomial function, Polynomial firstDerivative, Double start) {
		double zero = 0;
		
		//recursive
		return zero;
	}
}
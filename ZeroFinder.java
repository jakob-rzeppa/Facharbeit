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
		
		//edge cases (Derivative has no Zeros)
		if (zerosDerivative.size() == 0) {
			List<Double> zeros = new ArrayList<Double>();
			try {
				zeros.add(newtonmethod(function, firstDerivative, 0d));
				print(function, zeros);
			} catch (StackOverflowError e) {
				System.out.println("Degree: " + function.getDegree());
				System.out.println("Function: " + function.toString());
				System.out.println("No Zeros");
				System.out.println();
			}
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
			zeros.add(bisectormethod(function, areas.get(i)).get(0));
		}
		
		//edge cases
		if (function.getDegree() % 2 == 0) {
			if ((function.solve(zerosDerivative.get(0)) > 0 && function.getDegree() < 0) ||
				(function.solve(zerosDerivative.get(0)) < 0 && function.getDegree() > 0)) {
				zeros.add(newtonmethod(function, firstDerivative, zerosDerivative.get(0) - 1));
			}
			if ((function.solve(zerosDerivative.get(zerosDerivative.size() - 1)) > 0 && function.getDegree() < 0) ||
				(function.solve(zerosDerivative.get(zerosDerivative.size() - 1)) < 0 && function.getDegree() > 0)) {
				zeros.add(newtonmethod(function, firstDerivative, zerosDerivative.get(zerosDerivative.size() - 1) + 1));
			}
		} else {
			if ((function.solve(zerosDerivative.get(0)) > 0 && function.getDegree() > 0) ||
			(function.solve(zerosDerivative.get(0)) < 0 && function.getDegree() < 0)) {
				zeros.add(newtonmethod(function, firstDerivative, zerosDerivative.get(0) - 1));
			}
			if ((function.solve(zerosDerivative.get(zerosDerivative.size() - 1)) > 0 && function.getDegree() < 0) ||
				(function.solve(zerosDerivative.get(zerosDerivative.size() - 1)) < 0 && function.getDegree() > 0)) {;
				zeros.add(newtonmethod(function, firstDerivative, zerosDerivative.get(zerosDerivative.size() - 1) + 1));
			}
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

		for (int i = 0; i < zerosDerivative.size(); i++) {
			if (function.solve(zerosDerivative.get(i)) == 0) {
				zerosDerivative.remove(i);
			}
		}
		
		for (int i = 0; i < zerosDerivative.size() - 1; i++) {
			/*System.out.println(zerosDerivative.get(i));
			System.out.println(zerosDerivative.get(i+1));
			System.out.println();*/
			if (function.solve(zerosDerivative.get(i)) > 0 && function.solve(zerosDerivative.get(i + 1)) < 0 || 
				function.solve(zerosDerivative.get(i)) < 0 && function.solve(zerosDerivative.get(i + 1)) > 0) {
				//System.out.print("Area: " + zerosDerivative.get(i) + ", " + zerosDerivative.get(i + 1) + "\n");
				areas.add(new ArrayList<Double>());
				areas.get(areas.size() - 1).add(zerosDerivative.get(i));
				areas.get(areas.size() - 1).add(zerosDerivative.get(i + 1));
			}
		}
		
		return areas;
	}
	
	private static List<Double> bisectormethod(Polynomial function, List<Double> area) {
		double aV = function.solve(area.get(0));
		double bV = function.solve(area.get(1));

		double c = (area.get(0) + area.get(1)) / 2;
		double cV = function.solve(c);

		List<Double> newArea = new ArrayList<>();

		/*
		System.out.println("P1: " + area.get(0) + ": " + aV);
		System.out.println("P2: " + c + ": " + cV);
		System.out.println("P3: " + area.get(1) + ": " + bV);
		System.out.println();
		*/

		//recursive end
		if (Main.round(aV, 2) == 0) {
			return area;
		}

		if ((aV <= 0 && cV >= 0) ||
			(aV >= 0 && cV <= 0)) {
			newArea.add(area.get(0));
			newArea.add(c);
		} else if ((cV <= 0 && bV >= 0) ||
					(cV >= 0 && bV <= 0)) {
			newArea.add(c);
			newArea.add(area.get(1));
		} else {
			throw new RuntimeException("WTF ich versteh nix!");
		}

		//recursive
		return bisectormethod(function, newArea);
	}
	
	private static double newtonmethod(Polynomial function, Polynomial firstDerivative, Double value) {
		//recursive end
		if (function.solve(value) < 0.00001d && function.solve(value) > -0.00001d) {
			return value;
		}
		
		value = value - function.solve(value) / firstDerivative.solve(value);

		//recursive
		return newtonmethod(function, firstDerivative, value);
	}

	private static void print(Polynomial function, List<Double> zeros) {
		System.out.println("Degree: " + function.getDegree());
		System.out.println("Function: " + function.toString());
		System.out.println("Zero: " + zeros.get(0));
		System.out.println();
	}

	public static void main(String[] args) {
		List<Double> c = new ArrayList<Double>();
		c.add(-5d);
		c.add(1d);
		c.add(-10d);
		c.add(10d);
		Polynomial p = new Polynomial(c);
		List<Double> area = new ArrayList<>();
		area.add(0.05d);
		area.add(0.61d);
		//System.out.println(newtonmethod(p, p.derive(), 0.05d - 1d));
		//System.out.println(bisectormethod(p, area));
		//System.out.println(newtonmethod(p, p.derive(), 0.61d + 1d));
		List<Double> zerosDer = new ArrayList<Double>();
		zerosDer.add(0.05d);
		zerosDer.add(0.61d);
		//System.out.println(findAreas(p, p.derive(), zerosDer));
	}
}
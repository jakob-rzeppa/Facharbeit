import java.util.ArrayList;
import java.util.List;

class ZeroFinder {
	public static List<Double> findZeros(Polynomial function) {
		Polynomial firstDerivative = function.derive();
		Polynomial secDerivative = firstDerivative.derive();

		//recursive end
		if (function.getDegree() == 1) {
			List<Double> zeros = new ArrayList<Double>();
			zeros.add(Main.round(-(function.getCoefficients().get(0) / function.getCoefficients().get(1)), 4));

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
			zeros.add(bisectormethod(function, areas.get(i)).get(0));
		}
		
		System.out.println("!");
		//edge cases
		//TODO Ich hab keinen plan was hier abgeht
		if (function.getDegree() % 2 == 0) {
			System.out.println("Grade");
			if ((function.solve(zerosDerivative.get(0)) > 0 && function.getDegree() < 0) ||
				(function.solve(zerosDerivative.get(0)) < 0 && function.getDegree() > 0)) {
					System.out.println("2");
				zeros.add(newtonmethod(function, firstDerivative, zerosDerivative.get(0) - 1));
			}
			if ((function.solve(zerosDerivative.get(zerosDerivative.size() - 1)) > 0 && function.getDegree() < 0) ||
				(function.solve(zerosDerivative.get(zerosDerivative.size() - 1)) < 0 && function.getDegree() > 0)) {
				System.out.println("2");
				zeros.add(newtonmethod(function, firstDerivative, zerosDerivative.get(zerosDerivative.size() - 1) + 1));
			}
		} else {
			System.out.println("Ungrade");
			if ((function.solve(zerosDerivative.get(0)) > 0 && function.getDegree() > 0) ||
				(function.solve(zerosDerivative.get(0)) < 0 && function.getDegree() < 0)) {
					System.out.println("1");
				zeros.add(newtonmethod(function, firstDerivative, zerosDerivative.get(0) - 1));
			}
			if ((function.solve(zerosDerivative.get(zerosDerivative.size() - 1)) > 0 && function.getDegree() < 0) ||
				(function.solve(zerosDerivative.get(zerosDerivative.size() - 1)) < 0 && function.getDegree() > 0)) {
					System.out.println("1");
				zeros.add(newtonmethod(function, firstDerivative, zerosDerivative.get(zerosDerivative.size() - 1) + 1));
			}
		}
		System.out.println("?");
		
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
			System.out.println(zerosDerivative.get(i));
			System.out.println(zerosDerivative.get(i+1));
			System.out.println();
			if (function.solve(zerosDerivative.get(i)) > 0 && function.solve(zerosDerivative.get(i + 1)) < 0 || 
				function.solve(zerosDerivative.get(i)) < 0 && function.solve(zerosDerivative.get(i + 1)) > 0) {
				System.out.print("Area: " + zerosDerivative.get(i) + ", " + zerosDerivative.get(i + 1) + "\n");
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
		System.out.println(area.get(0) + ": " + aV);
		System.out.println(c + ": " + cV);
		System.out.println(area.get(1) + ": " + bV);
		System.out.println();
		*/

		//recursive end
		if (aV == 0) {
			return area;
		}

		if ((aV <= 0 && cV >= 0) ||
			(aV >= 0 && cV <= 0)) {
			newArea.add(Main.round(area.get(0), 2));
			newArea.add(Main.round(c, 2));
		} else if ((cV <= 0 && bV >= 0) ||
					(cV >= 0 && bV <= 0)) {
			newArea.add(Main.round(c, 2));
			newArea.add(Main.round(area.get(1), 2));
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
		return Main.round(newtonmethod(function, firstDerivative, value), 4);
	}

	public static void main(String[] args) {
		List<Double> c = new ArrayList<Double>();
		c.add(-5d);
		c.add(0d);
		c.add(2d);
		c.add(4d);
		c.add(-1d);
		Polynomial p = new Polynomial(c);
		List<Double> area = new ArrayList<>();
		area.add(-0.30d);
		area.add(0d);
		area.add(3.30d);
		//System.out.println(findAreas(p, p.derive(), area));
		System.out.println(findZeros(p));
	}
}
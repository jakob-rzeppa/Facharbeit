import java.util.ArrayList;
import java.util.List;

class ZeroFinder {
	public static List<Double> findZeros(Polynomial function) {
		Polynomial derivative = function.derive();
		Polynomial secDerivative = derivative.derive();
		
		List<Double> zeros = new ArrayList<Double>();

		// ---- recursive end ----
		if (function.getDegree() == 1) {
			zeros.add(-(function.getCoefficients().get(0) / function.getCoefficients().get(1)));

			print(function, zeros);
			return zeros;
		}
		
		// ---- recursive start ----
		List<Double> extremePoints = findZeros(derivative);
		
		//edge case (derivative has no zeros)
		if (extremePoints.size() == 0) {
			try {
				Double zero = newtonmethod(function, derivative, 0d, new double[2]);
				if (zero != null) {
					zeros.add(zero);
				}
			} catch (RuntimeException e) {
				System.out.println("problems with finding zeros when there are no extreme points");
				System.out.println(e.getMessage());
			} finally {
				print(function, zeros);
			}
			return zeros;
		}
		
		//edge case (zero lays on extreme point) => delete the extreme point so that the bisectormethod can find the zero
		for (int i = 0; i < extremePoints.size(); i++) {
			if (function.solve(extremePoints.get(i)) == 0) {
				extremePoints.remove(i);
			}
		}
		
		//between hightest/lowest extreme points
		try {
			List<List<Double>> areas = findAreas(function, secDerivative, extremePoints);
			for (int i = 0; i < areas.size(); i++) {
				zeros.add(bisectormethod(function, areas.get(i)).get(0));
			}
		} catch (RuntimeException e) {
			System.out.println("problems with finding zeros between hightest/lowest extreme points");
			System.out.println(e.getMessage());
		}
		
		//outside hightest/lowest extreme points
		try {
			for (double zero : zerosOutsideExtremePoints(function, derivative, extremePoints.get(0), extremePoints.get(extremePoints.size() - 1))) {
				zeros.add(zero);
			}
		} catch (RuntimeException e) {
			System.out.println("problems with finding zeros outside hightest/lowest extreme points");
			System.out.println(e.getMessage());
		}
		
		//sort (lowest to hightest)
		zeros.sort(null);
		
		print(function, zeros);
		
		return zeros;
	}

	private static List<Double> zerosOutsideExtremePoints(Polynomial function, Polynomial derivative, double derivativeFirstZero, double derivativeLastZero) {
		List<Double> zeros = new ArrayList<>();

		if (function.getDegree() % 2 == 0) { //straight
			//left
			if ((function.solve(derivativeFirstZero) > 0 && function.getDegree() < 0) || (function.solve(derivativeFirstZero) < 0 && function.getDegree() > 0)) {
				Double zero = newtonmethod(function, derivative, derivativeFirstZero - 1, new double[2]);
				if (zero != null) { zeros.add(zero); }
			}
			//right
			if ((function.solve(derivativeLastZero) > 0 && function.getDegree() < 0) || (function.solve(derivativeLastZero) < 0 && function.getDegree() > 0)) {
				Double zero = newtonmethod(function, derivative, derivativeLastZero + 1, new double[2]);
				if (zero != null) { zeros.add(zero); }
			}
		} else if (function.getDegree() % 2 != 0) { //odd
			//left
			if ((function.solve(derivativeFirstZero) > 0 && function.getDegree() > 0) || (function.solve(derivativeFirstZero) < 0 && function.getDegree() < 0)) {
				Double zero = newtonmethod(function, derivative, derivativeFirstZero - 1, new double[2]);
				if (zero != null) { zeros.add(zero); }
			}
			//right
			if ((function.solve(derivativeLastZero) > 0 && function.getDegree() < 0) || (function.solve(derivativeLastZero) < 0 && function.getDegree() > 0)) {
				Double zero = newtonmethod(function, derivative, derivativeLastZero + 1, new double[2]);
				if (zero != null) { zeros.add(zero); }
			}
		}
		return zeros;
	}
	
	private static List<List<Double>> findAreas(Polynomial function, Polynomial secDerivative, List<Double> extremePoints) {
		List<List<Double>> areas = new ArrayList<List<Double>>();

		for (int i = 0; i < extremePoints.size(); i++) {
			if (function.solve(extremePoints.get(i)) == 0) {
				extremePoints.remove(i);
			}
		}
		
		for (int i = 0; i < extremePoints.size() - 1; i++) {
			/*System.out.println(extremePoints.get(i));
			System.out.println(extremePoints.get(i+1));
			System.out.println();*/
			if (function.solve(extremePoints.get(i)) > 0 && function.solve(extremePoints.get(i + 1)) < 0 || 
				function.solve(extremePoints.get(i)) < 0 && function.solve(extremePoints.get(i + 1)) > 0) {
				//System.out.print("Area: " + extremePoints.get(i) + ", " + extremePoints.get(i + 1) + "\n");
				areas.add(new ArrayList<Double>());
				areas.get(areas.size() - 1).add(extremePoints.get(i));
				areas.get(areas.size() - 1).add(extremePoints.get(i + 1));
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
	
	private static Double newtonmethod(Polynomial function, Polynomial derivative, double value, double[] lastValues) {
		//recursive end
		if (function.solve(value) < 0.00001d && function.solve(value) > -0.00001d) {
			return value;
		}
		
		value = value - function.solve(value) / derivative.solve(value);

		//if the difference between the val before the last and the last is smaller than the difference between the last and current there is a infinite loop
		try {
			if (lastValues[1] - lastValues[0] > value - lastValues[1]) {
				return null;
			}
		} catch (RuntimeException e) {throw e;}

		//recursive
		return newtonmethod(function, derivative, value, lastValues);
	}

	private static void print(Polynomial function, List<Double> zeros) {
		System.out.println("Degree: " + function.getDegree());
		System.out.println("Function: " + function.toString());
		if (zeros.size() == 0) {
			System.out.println("No Zeros");
		}
		for (int i = 0; i < zeros.size(); i++) {
			System.out.println("Zero " + i + ": " + zeros.get(i));
		}
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
package NewMethod;

import java.util.List;
import java.util.ArrayList;

public class ZeroFinder {
    
    private static boolean running = true;





    private static List<Double> findZeros(RationalFunction function) {
        List<Double> zeros = new ArrayList<>();

        do {




        } while (running);

        return zeros;
    }

    private static Double newtonmethod(Polynomial function, Polynomial derivative, double value, Double[] lastValues) {
		//recursive end
		if (function.solve(value) < 0.00001d && function.solve(value) > -0.00001d) {
			return value;
		}
		
		value = value - function.solve(value) / derivative.solve(value);

		//if the difference between the val before the last and the last is smaller than the difference between the last and current there is a infinite loop
		if (lastValues[0] != null && lastValues[1] != null && lastValues[1] - lastValues[0] > value - lastValues[1]) {
            running = false;
			return null; 
		}

		lastValues[0] = lastValues[1];
		lastValues[1] = value;

		//recursive
		return newtonmethod(function, derivative, value, lastValues);
	}

    public static void main(String[] args) {
        
    }
}

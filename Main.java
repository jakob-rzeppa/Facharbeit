import java.util.List;
import java.util.Random;
import java.util.ArrayList;

class Main {
    public static void main(String[] args) {
        for (int i = 0; i < 1; i++) {
            List<Double> c = new ArrayList<>();
            
            Random rand = new Random();
            for (int j = 0; j < 10; j++) {
                c.add(round(rand.nextDouble(10d)-5d, 2));
            }

            Polynomial p = new Polynomial(c);

            System.out.println("----- " + p.toString() + " -----");

            ZeroFinder.findZeros(p);

            System.out.println();
            System.out.println("---------------------------------------------");
            System.out.println();
        }
    }

	public static double round(double value, int places) {
		if (places < 0) throw new IllegalArgumentException();
	
		long factor = (long) Math.pow(10, places);
		value = value * factor;
		long tmp = Math.round(value);
		return (double) tmp / factor;
	}
}
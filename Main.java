import java.util.List;

class Main {
    public static void main(String[] args) {
        //params
		Polynomial function = new Polynomial("3x^(4)+12x^(3)-20x^(2)+5x^(1)-7x^(0)");
		
		List<Double> zeros = ZeroFinder.findZeros(function);
		for (double zero : zeros) {
			System.out.println(zero);
		}
    }
}
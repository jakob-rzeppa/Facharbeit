import java.util.ArrayList;

class Main {
    public static void main(String[] args) {
        // params
        System.out.println("hi");
    }
	
	private static double[] findZeros(Polynom p) {
		Monomials[] m = p.getMonomials();
		if (p.getDegree == 1) {
			return -(m[m.length - 1].getCoefficient() / m[m.length - 2].getCoefficient());
		}
		double[][] areas = findAreas(p, p.derive(), p.derive().derive(), findZeros(p.derive());
		
	}
	
		//TODO change fixed values
		double[][] areas = new double[100][2];
		int ctrAreas = 0;
		//TODO ins Unendliche
		for (int i = 0; i < zeros_a.length - 1; i++) {
			if (!(p.solve(zeros_a[i]) > 0 > p.solve(zeros_a[i + 1]) || p.solve(zeros_a[i]) < 0 < p.solve(zeros_a[i + 1]))) {
				continue;
			}
			if (f_aa.solve(zeros_a[i]) = 0 || f_aa.solve(zeros_a[i + 1])) {
				continue;
			}
			areas[ctrAreas][0] = zeros_a[i];
			areas[ctrAreas][1] = zeros_a[i + 1];
			ctrAreas++;
		}
		return areas;
	}
	
	public static double[] bisectormethod(Polynom p, double[][] areas) {
		ArrayList<double> zeros = new ArrayList<double>();
	} 
}
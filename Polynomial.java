import java.util.List;
import java.util.ArrayList;

class Polynomial {
    private String content;
    private List<Monomial> monomials;

    // the content has to be in this form: 5x^(34)+65x^(33)+343x^(2)+23x^(1)+2x^(0)
    // every term has to be in this form: ax^(n) where a is a real number and n a natural number
    // always put the terms in descending order (by the exponent) and there may be no spaces
    public Polynomial(String content) {
        this.content = content;
        parseContent();
        checkContent();
    }

    public Polynomial(List<Monomial> monomials) {
        this.monomials = monomials;
        checkContent();
    }

    public double solve(double i) {
        int result = 0;
        for (Monomial m : monomials) {
            result += m.solve(i);
        }
        return result;
    }

    public Polynomial derive() {
        List<Monomial> derivativeMonomials = new ArrayList<Monomial>();
        for (int i = 0; i < monomials.size(); i++) {
			if (monomials.get(i).getExponent() != 0) {
            	derivativeMonomials.add(monomials.get(i).derive());
			}
        }
        return new Polynomial(derivativeMonomials);
    }

    // ---- get methods ----
    public List<Monomial> getMonomials() {
        return monomials;
    }
	public int getDegree() {
		return monomials.get(0).getExponent();
	}
	//TODO error handeling
	public double getZeroMonomialCoefficient() {
		return monomials.get(monomials.size() - 1).getCoefficient();
	}
	public double getFirstMonomialCoefficient() {
		return monomials.get(monomials.size() - 2).getCoefficient();
	}

    // ---- helper methods ----
    private void parseContent() {
        String tempContent = content.replace("+", "#+");
        tempContent = tempContent.replace("-", "#-");
        String[] contentSplit = tempContent.split("#");
        monomials = new ArrayList<Monomial>();
        for (int i = 0; i < contentSplit.length; i++) {
            monomials.add(new Monomial(contentSplit[i]));
        }
    }

    // maybe cause lag but now irrelevant: a monomial that is zero is still in storage and used
    private void checkContent() {
        content = "";
        for (Monomial m : monomials) {
            if (!m.isZero()) {
                if (m.getCoefficient() >= 0) {
                    content += "+";
                }
                content += m.toString();
            }
        }
    }


    // ---- override methods ----
    public String toString() {
        return content;
    }

   // ---- debug ----
    public static void main(String[] args) {
        Polynomial p = new Polynomial("5x^(5)+6x^(3)+12x^(1)+5x^(0)");
        System.out.println(p.toString());
        System.out.println(p.solve(-10));
    }
}
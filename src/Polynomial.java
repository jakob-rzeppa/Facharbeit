class Polynomial {
    private String content;
    private Monomial[] monomials;

    // the content has to be in this form: 5x^(34)+65x^(33)+343x^(2)+23x^(1)+2x^(0)
    // every term has to be in this form: ax^(n) where a is a real number and n a natural number
    // always put the terms in descending order (by the exponent) and there may be no spaces
    public Polynomial(String content) {
        this.content = content;
        parseContent();
        checkContent();
    }

    public Polynomial(Monomial[] monomials) {
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
        Monomial[] derivativeMonomials = new Monomial[monomials.length];
        for (int i = 0; i < monomials.length; i++) {
            derivativeMonomials[i] = monomials[i].derive();
        }
        return new Polynomial(derivativeMonomials);
    }

    // ---- get methods ----
    public Monomial[] getMonomials() {
        return monomials;
    }

    // ---- helper methods ----
    private void parseContent() {
        String tempContent = content.replace("+", "#+");
        tempContent = tempContent.replace("-", "#-");
        String[] contentSplit = tempContent.split("#");
        monomials = new Monomial[contentSplit.length];
        for (int i = 0; i < contentSplit.length; i++) {
            monomials[i] = new Monomial(contentSplit[i]);
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
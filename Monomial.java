class Monomial {
    private String content;
    private char variable;
    private double coefficient;
    private int exponent;
    private boolean isZero;

    public Monomial(String content) {
        this.content = content.replace("+", "");
        parseContent();
    }

    public Monomial(char variable, int exponent, double coefficient) {
        this.variable = variable;
        this.exponent = exponent;
        this.coefficient = coefficient;
        this.content = coefficient + "" + variable + "^(" + exponent + ")";
        checkIfZero();
    }

    public double solve(double i) {
        return Math.pow(i, exponent) * coefficient;
    }

    public Monomial derive() {
        return new Monomial(variable, exponent - 1, (coefficient * exponent));
    }

    // ---- get methods ----
    public char getVariable() {
        return variable;
    }
    public double getCoefficient() {
        return coefficient;
    }
    public int getExponent() {
        return exponent;
    }
    public boolean isZero() {
        return isZero;
    }

    // ---- helper methods ----
    // only possible for functions of that form: ax^(n) where a is a real number and n a natural number
    private void parseContent() {
        String[] contentSplit = content.split("");
        String[] coefficient_variable_exponent = new String[] {"","",""};

        int index = 0;

        // get the coefficient
        while (true) {
            try {
                Integer.parseInt(contentSplit[index]);
            } catch (NumberFormatException e) {
                if (!contentSplit[index].equals("-") && !contentSplit[index].equals(".")) {
                    break;
                }
            }
            coefficient_variable_exponent[0] += contentSplit[index++];
        }

        // get the variable
        coefficient_variable_exponent[1] = contentSplit[index];
        index += 3;

        //get the exponent
        while (true) {
            try {
                Integer.parseInt(contentSplit[index]);
            } catch (NumberFormatException e) {
                break;
            }
            coefficient_variable_exponent[2] += contentSplit[index++];
        }

        // set values in class
        this.variable = coefficient_variable_exponent[1].charAt(0);
        this.coefficient = Double.parseDouble(coefficient_variable_exponent[0]);
        this.exponent = Integer.parseInt(coefficient_variable_exponent[2]);
        checkIfZero();
    }

    private void checkIfZero() {
        if (exponent < 0 || coefficient == 0) {
            isZero = true;
        }
    }
    
    // ---- override methods ----
    public String toString() {
        return content;
    }

    // ---- debug ----
    public static void main(String[] args) {
        Monomial f = new Monomial('x', 2, -1.5);
        System.out.println(f.derive().toString());
    }
}

class Cond extends Special {

    // TODO: Add any fields needed.
    // TODO: Add an appropriate constructor.
    public Cond(Node t) {
    }

    void print(Node t, int n, boolean p) {
        Printer.printCond(t, n, p);
    }

    public Node eval(Node n, Environment env) {
        Node exp;
        exp = n.getCdr();

        while ((!(exp.getCar().getCar().eval(env).getBool()))
                && (!exp.isNull())) {
            exp = exp.getCdr();
        }

        if (exp.isNull()) {
            return new Nil();
        } else {
            return (exp.getCar().getCdr().getCar().eval(env));
        }
    }
}

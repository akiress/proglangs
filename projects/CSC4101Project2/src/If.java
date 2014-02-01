
class If extends Special {

    // TODO: Add any fields needed.
    // TODO: Add an appropriate constructor.
    public If(Node t) {
    }

    void print(Node t, int n, boolean p) {
        Printer.printIf(t, n, p);
    }

    public Node eval(Node n, Environment env) {
        Node cond;
        Node exp;
        cond = n.getCdr().getCar();

        if (cond.eval(env).getBool()) {
            exp = n.getCdr().getCdr().getCar();
            return exp.eval(env);
        } else if (!(n.getCdr().getCdr().getCdr().isNull())) {
            exp = n.getCdr().getCdr().getCdr().getCar();
            return exp.eval(env);
        }
        System.err.println("Error in if.eval");
        return new Nil();
    }
}

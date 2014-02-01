
class Lambda extends Special {

    // TODO: Add any fields needed.
    // TODO: Add an appropriate constructor.
    public Lambda(Node t) {
    }

    void print(Node t, int n, boolean p) {
        Printer.printLambda(t, n, p);
    }

    public Node eval(Node n, Environment env) {
        return new Closure(n.getCdr(), env);
    }
}

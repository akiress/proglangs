
class Set extends Special {

    // TODO: Add any fields needed.
    // TODO: Add an appropriate constructor.
    public Set(Node t) {
    }

    void print(Node t, int n, boolean p) {
        Printer.printSet(t, n, p);
    }

    public Node eval(Node n, Environment env) {
        Node id;
        Node exp;

        id = n.getCdr().getCar();
        exp = n.getCdr().getCdr().getCar();

        env.define(id, exp.eval(env));
        return new StrLit("");
    }
}

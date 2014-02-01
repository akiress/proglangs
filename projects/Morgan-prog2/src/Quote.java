
class Quote extends Special {

    // TODO: Add any fields needed.
    // TODO: Add an appropriate constructor.
    public Quote(Node t) {
    }

    @Override
    void print(Node t, int n, boolean p) {
        Printer.printQuote(t, n, p);
    }

    @Override
    public Node eval(Node n, Environment env) {
        return n.getCdr().getCar();
    }
}

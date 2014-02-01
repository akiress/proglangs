
class Define extends Special {

    // TODO: Add any fields needed.
    // TODO: Add an appropriate constructor.
    public Define(Node t) {
    }

    @Override
    void print(Node t, int n, boolean p) {
        Printer.printDefine(t, n, p);
    }

    @Override
    public Node eval(Node n, Environment env) {
        Node id;
        Node value;
        
        id = n.getCdr().getCar();
        value = n.getCdr().getCdr().getCar();

        if (id.isSymbol()) {
            env.define(id, value);
        } else {
            Closure func = new Closure(new Cons(n.getCdr().getCar().getCdr(), n
                    .getCdr().getCdr()), env);
            env.define(id.getCar(), func);
        }

        return new StrLit("; no values returned");
    }
}

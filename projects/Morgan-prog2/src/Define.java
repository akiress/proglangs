
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
            System.out.println("In define.java-if");
            env.define(id, value);
        } else {
            System.out.println("in define.java-else");
            Closure func = new Closure(new Cons(n.getCdr().getCar().getCdr(), n
                    .getCdr().getCdr()), env);
            env.define(id.getCar(), func);
        }

        return new StrLit("No values define.eval");
    }
}

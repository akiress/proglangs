
class Let extends Special {

    // TODO: Add any fields needed.
    // TODO: Add an appropriate constructor.
    public Let(Node t) {
    }

    void print(Node t, int n, boolean p) {
        Printer.printLet(t, n, p);
    }

    public Node eval(Node n, Environment env) {
        Node args;
        Node exp;
        Environment local = new Environment(env);
        args = n.getCdr().getCar();
        exp = n.getCdr().getCdr().getCar();
        args = evalLet(args, local);

        return exp.eval(local);
    }

    public Node evalLet(Node n, Environment env) {
        if (n == null || n.isNull()) {
            Node list = new Cons(new Nil(), new Nil());
            return list;
        } else {
            Node arg;
            Node exp;
            Node rest;
            arg = n.getCar().getCar();
            exp = n.getCar().getCdr().getCar();
            rest = n.getCdr();

            if (arg.isSymbol()) {
                env.define(arg, exp.eval(env));
                return evalLet(rest, env);
            } else if (arg.isPair()) {
                return arg.eval(env);
            } else if (arg == null || arg.isNull()) {
                return new Nil();
            }
        }
        return null;
    }
}
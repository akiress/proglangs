
class Regular extends Special {

    // TODO: Add any fields needed.
    // TODO: Add an appropriate constructor.
    public Regular(Node t) {
    }

    @Override
    void print(Node t, int n, boolean p) {
        Printer.printRegular(t, n, p);
    }

    @Override
    public Node eval(Node n, Environment env) {
        Node front;
        Node args;

        front = n.getCar();
        args = evalRegular(n.getCdr(), env);

        while (front.isSymbol()) {
            front = env.lookup(front);
        }

        if (front == null || front.isNull()) {
            System.err.println("Regular.eval error");
            return new Nil();
        }

        if (front.isProcedure()) {
            return front.apply(args);
        } else {
            return front.eval(env).apply(args);
        }
    }

    public Node evalRegular(Node n, Environment env) {
        if (n == null || n.isNull()) {
            Node list = new Cons(new Nil(), new Nil());
            return list;
        } else {
            Node arg1;
            Node rest;

            arg1 = n.getCar();
            rest = n.getCdr();

            if (arg1.isSymbol()) {
                arg1 = env.lookup(arg1);
            }

            if (arg1 == null || arg1.isNull()) {
                return new Nil();
            }

            Node list = new Cons(arg1.eval(env), evalRegular(rest, env));
            return list;
        }
    }
}

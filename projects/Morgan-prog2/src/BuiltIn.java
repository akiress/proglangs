// BuiltIn.java -- the data structure for function closures

// Class BuiltIn is used for representing the value of built-in functions
// such as +.  Populate the initial environment with
// (name, new BuiltIn(name)) pairs.
// The object-oriented style for implementing built-in functions would be
// to include the Java methods for implementing a Scheme built-in in the
// BuiltIn object.  This could be done by writing one subclass of class
// BuiltIn for each built-in function and implementing the method apply
// appropriately.  This requires a large number of classes, though.
// Another alternative is to program BuiltIn.apply() in a functional
// style by writing a large if-then-else chain that tests the name of
// of the function symbol.
class BuiltIn extends Node {

    private Node symbol;
    static Environment interaction_environment = new Environment();

    public BuiltIn(Node s) {
        symbol = s;
    }

    public BuiltIn(Environment env) {
        interaction_environment = env;
    }

    public Node getSymbol() {
        return symbol;
    }

    // TODO: The method isProcedure() should be defined in
    // class Node to return false.
    @Override
    public boolean isProcedure() {
        return true;
    }

    @Override
    public void print(int n) {
        // there got to be a more efficient way to print n spaces
        for (int i = 0; i < n; i++) {
            System.out.print(" ");
        }
        System.out.println("#{Built-in Procedure");
        symbol.print(n + 3);
        for (int i = 0; i < n; i++) {
            System.out.print(" ");
        }
        System.out.println("}");
    }

    // TODO: The method apply() should be defined in class Node
    // to report an error. It should be overwritten only in classes
    // BuiltIn and Closure.
    @Override
    public Node apply(Node args) {
        String symbolName = symbol.getName();
        Node arg1 = args.getCar();
        if (arg1 == null || arg1.isNull()) {
            arg1 = new Nil();
        }

        Node arg2 = args.getCdr();
        if (arg2 == null || arg2.isNull()) {
            arg2 = new Nil();
        } else {
            arg2 = arg2.getCar();
        }
        if (symbolName.equals("b+")) {
            if (arg1.isNumber() && arg2.isNumber()) {
                int x = arg1.getVal();
                int y = arg2.getVal();
                return new IntLit(x + y);
            } else {
                System.err.println("Error: Bad argument for b+");
                return new StrLit("");
            }
        } else if (symbolName.equals("b-")) {
            if (arg1.isNumber() && arg2.isNumber()) {
                int x = arg1.getVal();
                int y = arg2.getVal();
                return new IntLit(x - y);
            } else {
                System.err.println("Error: Bad argument for b-");
                return new StrLit("");
            }
        } else if (symbolName.equals("b/")) {
            if (arg1.isNumber() && arg2.isNumber()) {
                int x = arg1.getVal();
                int y = arg2.getVal();
                return new IntLit(x / y);
            } else {
                System.err.println("Error: Bad argument for b/");
                return new StrLit("");
            }
        } else if (symbolName.equals("b*")) {
            if (arg1.isNumber() && arg2.isNumber()) {
                int x = arg1.getVal();
                int y = arg2.getVal();
                return new IntLit(x * y);
            } else {
                System.err.println("Error: Bad argument for b*");
                return new StrLit("");
            }
        } else if (symbolName.equals("b<")) {
            if (arg1.isNumber() && arg2.isNumber()) {
                int x = arg1.getVal();
                int y = arg2.getVal();
                return new BooleanLit(x < y);
            } else {
                System.err.println("Error: Bad argument for b<");
            }
        } else if (symbolName.equals("b>")) {
            if (arg1.isNumber() && arg2.isNumber()) {
                int x = arg1.getVal();
                int y = arg2.getVal();
                return new BooleanLit(x > y);
            } else {
                System.err.println("Error: Bad argument for b>");
            }
        } else if (symbolName.equals("b=")) {
            if (arg1.isNumber() && arg2.isNumber()) {
                int x = arg1.getVal();
                int y = arg2.getVal();
                return new BooleanLit(x == y);
            } else {
                System.err.println("Error: Bad argument for b=");
            }
        } else if (symbolName.equals("number?")) {
            return new BooleanLit(arg1.isNumber());
        } else if (symbolName.equals("eval")) {
            return arg1;
        } else if (symbolName.equals("null?")) {
            return new BooleanLit(arg1.isNull());
        } else if (symbolName.equals("car")) {
            if (arg1.isNull()) {
                return arg1;
            }
            return arg1.getCar();
        } else if (symbolName.equals("cdr")) {
            if (arg1.isNull()) {
                return arg1;
            }
            return arg1.getCdr();
        } else if (symbolName.equals("cons")) {
            return new Cons(arg1, arg2);
        } else if (symbolName.equals("pair?")) {
            return new BooleanLit(arg1.isPair());
        } else if (symbolName.equals("symbol?")) {
            return new BooleanLit(arg1.isSymbol());
        } else if (symbolName.equals("set-car!")) {
            arg1.setCar(arg2);
            return arg1;
        } else if (symbolName.equals("set-cdr!")) {
            arg1.setCdr(arg2);
            return arg1;
        } else if (symbolName.equals("procedure?")) {
            return new BooleanLit(arg1.isProcedure());
        } else if (symbolName.equals("newline")) {
            return new StrLit("", false);
        } else if (symbolName.equals("eq?")) {
            if (arg1.isBoolean() && arg2.isBoolean()) {
                return new BooleanLit(arg1.getBool() == arg2.getBool());
            } else if (arg1.isNumber() && arg2.isNumber()) {
                return new BooleanLit(arg1.getVal() == arg2.getVal());
            } else if (arg1.isString() && arg2.isString()) {
                return new BooleanLit(arg1.getName().equals(arg2.getName()));
            } else if (arg1.isSymbol() && arg2.isSymbol()) {
                return new BooleanLit(arg1.getName().equals(arg2.getName()));
            } else if (arg1.isNull() && arg2.isNull()) {
                return new BooleanLit(true);
            } else if (arg1.isPair() && arg2.isPair()) {
                Node frontArgs = new Cons(arg1.getCar(), new Cons(
                        arg2.getCar(), new Nil()));
                Node backArgs = new Cons(arg1.getCdr(), new Cons(arg2.getCdr(),
                        new Nil()));
                return new BooleanLit(apply(frontArgs).getBool()
                        && apply(backArgs).getBool());
            }
            return new BooleanLit(false);
        } else if (symbolName.equals("write")) {
            arg1.print(0);
            return new StrLit("");
        } else if (symbolName.equals("interaction-environment")) {
            interaction_environment.print(0);
        } else if (symbolName.equals("apply")) {
            return arg1.apply(arg2);
        } else if (symbolName.equals("exit")) {
            System.exit(0);
        } else {
            System.err
                    .println("BuiltIn function " + symbolName + " not found!");
        }
        return new StrLit("");
    }

    public Node eval(Node n, Environment env) {
        return new Nil();
    }
}
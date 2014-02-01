
class Node {
    // The argument of print(int) is the number of characters to indent.
    // Every subclass of Node must implement print(int).

    public void print(int n) {
    }

    // The first argument of print(int, boolean) is the number of characters
    // to indent. It is interpreted the same as for print(int).
    // The second argument is only useful for lists (nodes of classes
    // Cons or Nil). For all other subclasses of Node, the booleanean
    // argument is ignored. Therefore, print(n,p) defaults to print(n)
    // for all classes other than Cons and Nil.
    // For classes Cons and Nil, print(n,TRUE) means that the open
    // parenthesis was printed already by the caller.
    // Only classes Cons and Nil override print(int,boolean).
    // For correctly indenting special forms, you might need to pass
    // additional information to print. What additional information
    // you pass is up to you. If you only need one more bit, you can
    // encode that in the sign bit of n. If you need additional parameters,
    // make sure that you define the method print in all the appropriate
    // subclasses of Node as well.
    public void print(int n, boolean p) {
        print(n);
    }

    // For parsing Cons nodes, for printing trees, and later for
    // evaluating them, we need some helper functions that test
    // the type of a node and that extract some information.
    // TODO: implement these in the appropriate subclasses to return TRUE.
    public boolean isBoolean() {
        return false;
    } // BooleanLit //done

    public boolean isNumber() {
        return false;
    } // IntLit // done

    public boolean isString() {
        return false;
    } // StringLit //done

    public boolean isSymbol() {
        return false;
    } // Ident // done

    public boolean isNull() {
        return false;
    } // nil // done

    public boolean isPair() {
        return false;
    } // Cons // done

    // TODO: Report an error in these default methods and implement them
    // in class Cons. After setCar, a Cons cell needs to be `parsed' again
    // using parseList.
    public static void print(Node t, int n, boolean p) {
        t.print(n, p);
    }

    public static Node getCar(Node t) {
        return t.getCar();
    }

    public static Node getCdr(Node t) {
        return t.getCdr();
    }
    
    public Node getCar() {
        return null;
    }
    
    public Node getCdr() {
        return null;
    }
    
    public void setCar(Node a) {
    }
    
    public void setCdr(Node d) {
    }

    public static boolean isNull(Node t) {
        return t.isNull();
    }

    public static boolean isPair(Node t) {
        return t.isPair();
    }

    public Node eval(Environment env) {
        return this;
    }

    public int getVal() {
        return 0;
    }

    public boolean getBool() {
        return false;
    }

    public String getName() {
        return null;
    }

    public boolean isProcedure() {
        return false;
    }

    public Node apply(Node args) {
        return null;
    }
}
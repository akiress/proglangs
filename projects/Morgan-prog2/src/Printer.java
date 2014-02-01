
public class Printer {

    public static void indent(int n) {
        if (n < 0) {
            n = -n;
        } else {
            for (int i = 0; i < n; i++) {
                System.out.print(" ");
            }
        }
    }

    public static void printBoolLit(int n, int boolVal) {
        indent(n);
        if (boolVal == 1) {
            System.out.println("#t");
        } else {
            System.out.println("#f");
        }
    }

    public static void printIntLit(int n, int intVal) {
        indent(n);
        System.out.println(intVal);
    }

    public static void printStrLit(int n, String strVal) {
        indent(n);
        System.out.println("\"" + strVal + "\"");
    }

    public static void printIdent(int n, String name) {
        indent(n);
        System.out.println(name);
    }
    
    public static void printNil(int n, boolean p) {
        indent(n);
        System.out.println(p);
    }

//    public static void printNil(int n, boolean p) {
//        indent(n);
//        if (p) {
//            System.out.println(")");
//        } else {
//            System.out.println("()");
//        }
//    }

    public static void printQuote(Node t, int n, boolean p) {
        Node e = Node.getCar(Node.getCdr(t));
        if (e != null) {
            indent(n);
            System.out.println("'");
            Node.print(e, -n - 1, false);
        } else {
            printRegular(t, n, p);
        }
    }

    public static void printLambda(Node t, int n, boolean p) {
        indent(n);
        if (!p) {
            System.out.print("(LAMBDA");
            Node.print(Node.getCdr(t), n, true);
        } else {
            printRegular(t, n, p);
        }
    }

    public static void printBegin(Node t, int n, boolean p) {
        indent(n);
        if (!p) {
            System.out.println("(BEGIN");
            Node.print(Node.getCdr(t), n, true);
        } else {
            printRegular(t, n, p);
        }
    }

    public static void printIf(Node t, int n, boolean p) {
        indent(n);
        if (!p) {
            System.out.println("(IF");
            Node.print(Node.getCdr(t), n, true);
        } else {
            printRegular(t, n, p);
        }
    }

    public static void printLet(Node t, int n, boolean p) {
        indent(n);
        if (!p) {
            System.out.println("(LET");
            Node.print(Node.getCdr(t), n, true);
        } else {
            printRegular(t, n, p);
        }
    }

    public static void printCond(Node t, int n, boolean p) {
        indent(n);
        if (!p) {
            System.out.println("(COND");
            Node.print(Node.getCdr(t), n, true);
        } else {
            printRegular(t, n, p);
        }
    }

    public static void printDefine(Node t, int n, boolean p) {
        indent(n);
        if (!p) {
            System.out.print("(DEFINE");
            Node.print(Node.getCdr(t), n, true);
        } else {
            printRegular(t, n, p);
        }
    }

    public static void printSet(Node t, int n, boolean p) {
        indent(n);
        if (!p) {
            System.out.println("(SET!");
            Node.print(Node.getCdr(t), n, true);
        } else {
            printRegular(t, n, p);
        }
    }

    public static void printRegular(Node t, int n, boolean p) {
        if (!p) {
            indent(n);
            System.out.println('(');
        }
        Node.print(Node.getCar(t), n + 2, false);
        Node d = Node.getCdr(t);
        if ((Node.isNull(d)) || (Node.isPair(d))) {
            Node.print(d, n, true);
        } else {
            indent(n);
            System.out.println('.');
            Node.print(d, n + 2, false);
            indent(n);
            System.out.println(')');
        }
    }

    public static void main(String[] args) {
    }
}

class BooleanLit extends Node {

    private boolean booleanVal;
    private static BooleanLit True = null;
    private static BooleanLit False = null;

    public BooleanLit(boolean b) {
        booleanVal = b;
    }

    public static BooleanLit getInstance(boolean val) {
        if (val == true) {
            if (True == null) {
                True = new BooleanLit(true);
            }
            return True;
        } else {
            if (False == null) {
                False = new BooleanLit(false);
            }
            return False;
        }
    }

    @Override
    public void print(int n) {

        if (booleanVal) {
            Printer.printBoolLit(n, 1);
        } else {
            Printer.printBoolLit(n, 0);
        }
    }

    @Override
    public boolean isBoolean() {
        return true;
    }

    @Override
    public boolean getBool() {
        return booleanVal;
    }

    public Node eval(Node p, Environment env) {
        return this;
    }
}

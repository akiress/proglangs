
class StrLit extends Node {

    private String strVal;
    private boolean quoteString;

    public StrLit(String s) {
        strVal = s;
    }

    public StrLit(String s, boolean b) {
        strVal = s;
        setQuoteString(b);
    }

    public void print(int n) {
        Printer.printStrLit(n, strVal);
    }

    public boolean isString() {
        return true;
    }

    public String getStrVal() {
        return strVal;
    }

    public Node eval(Node p, Environment env) {
        return this;
    }

    public boolean isQuoteString() {
        return quoteString;
    }

    public void setQuoteString(boolean quoteString) {
        this.quoteString = quoteString;
    }
}

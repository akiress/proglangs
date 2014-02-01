
class Begin extends Special {

    // TODO: Add any fields needed.
    
    // does constructor need to take Node or just be () ??
    public Begin(Node t) {
    }

    void print(Node t, int n, boolean p) {
        Printer.printBegin(t, n, p);

    }

    public Node eval(Node n, Environment env) {
        return null;
    }
}

class Quote extends Special {
	public Quote(Node t) {
	}

	void print(Node t, int n, boolean p) {
		Printer.printQuote(t, n, p);
	}

	public Node eval(Node t, Environment env) {
		return t.getCdr().getCar();
	}
}

class Begin extends Special {
	public Begin(Node t) {
	}

	void print(Node t, int n, boolean p) {
		Printer.printBegin(t, n, p);
	}

	public Node eval(Node t, Environment env) {
		return null;
	}
}

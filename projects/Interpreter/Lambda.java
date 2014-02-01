class Lambda extends Special {

	public Lambda(Node t) {
	}

	void print(Node t, int n, boolean p) {
		Printer.printLambda(t, n, p);
	}

	public Node eval(Node t, Environment env) {
		return new Closure(t.getCdr(), env);
	}
}

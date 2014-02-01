class If extends Special {

	public If(Node t) {
	}

	void print(Node t, int n, boolean p) {
		Printer.printIf(t, n, p);
	}

	public Node eval(Node t, Environment env) {
		Node cond;
		Node exp;
		cond = t.getCdr().getCar();
		if (cond.eval(env).getBoolean()) {
			exp = t.getCdr().getCdr().getCar();
			return exp.eval(env);
		} else if (!(t.getCdr().getCdr().getCdr()).isNull()) {
			exp = t.getCdr().getCdr().getCdr().getCar();
			return exp.eval(env);
		} else {
			System.err.println("No Else Expression");
			return new Nil();
		}
	}
}

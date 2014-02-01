class Cond extends Special {

	public Cond(Node t) {
	}

	void print(Node t, int n, boolean p) {
		Printer.printCond(t, n, p);
	}

	public Node eval(Node t, Environment env) {
		Node exp;
		exp = t.getCdr();

		while ((!(exp.getCar()).getCar().eval(env).getBoolean())
				&& (!exp.isNull())) {
			exp = exp.getCdr();
		}

		if (exp.isNull()) {
			return new Nil();
		} else {
			return (exp.getCar().getCdr().getCar().eval(env));
		}
	}
}

class Set extends Special {

	public Set(Node t) {
	}

	void print(Node t, int n, boolean p) {
		Printer.printSet(t, n, p);
	}

	public Node eval(Node t, Environment env) {
		Node id;
		Node exp;
		id = t.getCdr().getCar();
		exp = t.getCdr().getCdr().getCar();
		env.define(id, exp.eval(env));
		return new StrLit("");
	}
}

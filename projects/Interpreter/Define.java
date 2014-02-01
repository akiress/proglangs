class Define extends Special {

	public Define(Node t) {
	}

	void print(Node t, int n, boolean p) {
		Printer.printDefine(t, n, p);
	}

	public Node eval(Node t, Environment env) {
		Node id;
		Node val;

		id = t.getCdr().getCar();
		val = t.getCdr().getCdr().getCar();

		if (id.isSymbol()) {
			env.define(id, val);
		} else {
			Closure func = new Closure(new Cons(t.getCdr().getCar().getCdr(), t
					.getCdr().getCdr()), env);
			env.define(id.getCar(), func);
		}

		return new StrLit("; no values returned");
	}
}

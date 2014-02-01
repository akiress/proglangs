class Let extends Special {
	public Let(Node t) {
	}

	void print(Node t, int n, boolean p) {
		Printer.printLet(t, n, p);
	}

	public Node eval(Node t, Environment env) {
		Node args;
		Node exp;
		Environment local = new Environment(env);
		args = t.getCdr().getCar();
		exp = t.getCdr().getCdr().getCar();
		args = eval_body(args, local);
		return exp.eval(local);
	}

	public Node eval_body(Node t, Environment env) {
		if (t == null || t.isNull()) {
			Node list = new Cons(new Nil(), new Nil());
			return list;
		} else {
			Node arg, exp, rest;
			arg = t.getCar().getCar();
			exp = t.getCar().getCdr().getCar();
			rest = t.getCdr();

			if (arg.isSymbol()) {
				env.define(arg, exp.eval(env));
				return eval_body(rest, env);
			} else if (arg.isPair()) {
				return arg.eval(env);
			} else if (arg == null || arg.isNull()) {
				return new Nil();
			}
		}
		return null;
	}
}

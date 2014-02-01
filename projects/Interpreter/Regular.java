class Regular extends Special {

	public Regular(Node t) {
	}

	void print(Node t, int n, boolean p) {
		// Printer.printRegular(t, n, p);
		if (Environment.errorMessages.size() == 0) {
			Printer.printQuoted(t, n, true);
			System.out.print(")");
			System.out.println();
		} else {
			String newErr = "";
			for (String s : Environment.errorMessages) {
				String tmp = s;
				if (!tmp.equals(newErr)) {
					System.out.println(s);
				} else {
					newErr = tmp;
				}
			}
		}
	}

	public Node eval(Node t, Environment env) {
		Node first;
		Node args;

		first = t.getCar();
		args = eval_list(t.getCdr(), env);

		while (first.isSymbol()) {
			first = env.lookup(first);
		}

		if (first == null || first.isNull()) {
			return null;
		}
		if (first.isProcedure()) {
			return first.apply(args);
		} else {
			return first.eval(env).apply(args);
		}

	}

	public Node eval_list(Node t, Environment env) {
		if (t == null || t.isNull()) {
			Node list = new Cons(new Nil(), new Nil());
			return list;
		} else {
			Node arg1, rest;
			arg1 = t.getCar();
			rest = t.getCdr();

			if (arg1.isSymbol()) {
				arg1 = env.lookup(arg1);
			}
			if (arg1 == null || arg1.isNull()) {
				return null;
			}
			Node list = new Cons(arg1.eval(env), eval_list(rest, env));
			return list;
		}
	}
}

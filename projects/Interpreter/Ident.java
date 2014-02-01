class Ident extends Node {

	private String name;

	public Ident(String n) {
		name = n;
	}

	public Node eval(Node t, Environment env) {
		Node args;
		Node a = new Cons(new Ident(t.getName()), new Nil());
		args = eval_list(a, env);
		if (!args.isNull()) {
			if (args.getCar().isPair()) {
				if (Environment.errorMessages.size() == 0) {
					Printer.printQuoted(args, 0, true);
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
			} else if (args.getCar().isNumber()) {
				return new IntLit(args.getCar().getVal());
			} else if (args.getCar().isString()) {
				return new StrLit(args.getCar().getStrVal());
			} else if (args.getCar().isBoolean()) {
				return new BooleanLit(args.getCar().getBoolean());
			} else {
				System.out.println("placeholder");
				return new Nil();
			}
		} else {
			return null;
		}
		return new StrLit("");
	}

	public Node eval_list(Node t, Environment env) {
		if (t == null || t.isNull()) {
			Node list = new Cons(new Nil(), new Nil());
			return list;
		} else {
			Node arg1;
			Node rest;
			arg1 = t.getCar();
			rest = t.getCdr();

			if (arg1.isSymbol()) {
				arg1 = env.lookup(arg1);
			}
			if (arg1 == null || arg1.isNull()) {
				return new Nil();
			}
			Node list = new Cons(arg1.eval(env), eval_list(rest, env));
			return list;
		}
	}

	public void print(int n) {
		Printer.printIdent(n, name);
	}

	public boolean isSymbol() {
		return true;
	}

	public String getSymbol() {
		return name;
	}

	public String getName() {
		return name;
	}
}

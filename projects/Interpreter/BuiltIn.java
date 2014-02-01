class BuiltIn extends Node {

	private Node symbol;
	protected static Environment interaction_environment = new Environment();

	public BuiltIn(Node s) {
		symbol = s;
	}

	public BuiltIn(Environment env) {
		interaction_environment = env;
	}

	public Node getSymbol() {
		return symbol;
	}

	public boolean isProcedure() {
		return true;
	}

	public void print(int n) {
		Printer.indent(n);
		System.out.println("#{Built-in Procedure");
		symbol.print(n + 3);
		for (int i = 0; i < n; i++) {
			System.out.print(" ");
		}
		System.out.println('}');
	}

	public Node apply(Node args) {
		if (args == null) {
			return null;
		}
		String symbolName = symbol.getName();
		Node arg1 = args.getCar();
		if (arg1 == null || arg1.isNull()) {
			arg1 = new Nil();
		}

		Node arg2 = args.getCdr();
		if (arg2 == null || arg2.isNull()) {
			arg2 = new Nil();
		} else {
			arg2 = arg2.getCar();
		}
		if (symbolName.equals("b+")) {
			if (arg1.isNumber() && arg2.isNumber()) {
				int x = arg1.getVal();
				int y = arg2.getVal();
				return new IntLit(x + y);
			} else {
				System.err.println("Error: Bad argument for b+");
				return new StrLit("");
			}
		} else if (symbolName.equals("b-")) {
			if (arg1.isNumber() && arg2.isNumber()) {
				int x = arg1.getVal();
				int y = arg2.getVal();
				return new IntLit(x - y);
			} else {
				System.err.println("Error: Bad argument for b-");
				return new StrLit("");
			}
		} else if (symbolName.equals("b*")) {
			if (arg1.isNumber() && arg2.isNumber()) {
				int x = arg1.getVal();
				int y = arg2.getVal();
				return new IntLit(x * y);
			} else {
				System.err.println("Error: Bad argument for b*");
				return new StrLit("");
			}
		} else if (symbolName.equals("b/")) {
			if (arg1.isNumber() && arg2.isNumber()) {
				int x = arg1.getVal();
				int y = arg2.getVal();
				return new IntLit(x / y);
			} else {
				System.err.println("Error: Bad argument for b/");
				return new StrLit("");
			}
		} else if (symbolName.equals("b=")) {
			if (arg1.isNumber() && arg2.isNumber()) {
				int x = arg1.getVal();
				int y = arg2.getVal();
				return new BooleanLit(x == y);
			} else {
				System.err.println("Error: Bad argument for b=");
			}
		} else if (symbolName.equals("b<")) {
			if (arg1.isNumber() && arg2.isNumber()) {
				int x = arg1.getVal();
				int y = arg2.getVal();
				return new BooleanLit(x < y);
			} else {
				System.err.println("Error: Bad argument for b<");
			}
		} else if (symbolName.equals("b>")) {
			if (arg1.isNumber() && arg2.isNumber()) {
				int x = arg1.getVal();
				int y = arg2.getVal();
				return new BooleanLit(x > y);
			} else {
				System.err.println("Error: Bad argument for b>");
			}
		} else if (symbolName.equals("car")) {
			if (arg1.isNull()) {
				return arg1;
			}
			return arg1.getCar();
		} else if (symbolName.equals("cdr")) {
			if (arg1.isNull()) {
				return arg1;
			}
			return arg1.getCdr();
		} else if (symbolName.equals("cons")) {
			return new Cons(arg1, arg2);
		} else if (symbolName.equals("set-car!")) {
			arg1.setCar(arg2);
			return arg1;
		} else if (symbolName.equals("set-cdr!")) {
			arg1.setCdr(arg2);
			return arg1;
		} else if (symbolName.equals("symbol?")) {
			return new BooleanLit(arg1.isSymbol());
		} else if (symbolName.equals("number?")) {
			return new BooleanLit(arg1.isNumber());
		} else if (symbolName.equals("null?")) {
			return new BooleanLit(arg1.isNull());
		} else if (symbolName.equals("pair?")) {
			return new BooleanLit(arg1.isPair());
		} else if (symbolName.equals("eq?")) {
			if (arg1.isBoolean() && arg2.isBoolean()) {
				return new BooleanLit(arg1.getBoolean() == arg2.getBoolean());
			} else if (arg1.isNumber() && arg2.isNumber()) {
				return new BooleanLit(arg1.getVal() == arg2.getVal());
			} else if (arg1.isString() && arg2.isString()) {
				return new BooleanLit(arg1.getStrVal().equals(arg2.getStrVal()));
			} else if (arg1.isSymbol() && arg2.isSymbol()) {
				return new BooleanLit(arg1.getName().equals(arg2.getName()));
			} else if (arg1.isNull() && arg2.isNull()) {
				return new BooleanLit(true);
			} else if (arg1.isPair() && arg2.isPair()) {
				Node frontArgs = new Cons(arg1.getCar(), new Cons(
						arg2.getCar(), new Nil()));
				Node backArgs = new Cons(arg1.getCdr(), new Cons(arg2.getCdr(),
						new Nil()));
				return new BooleanLit(apply(frontArgs).getBoolean()
						&& apply(backArgs).getBoolean());
			}
			return new BooleanLit(false);
		} else if (symbolName.equals("procedure?")) {
			return new BooleanLit(arg1.isProcedure());
		} else if (symbolName.equals("display")) {
			return arg1;
		} else if (symbolName.equals("newline")) {
			return new StrLit("", false);
		} else if (symbolName.equals("exit") || symbolName.equals("quit")) {
			System.exit(0);
		} else if (symbolName.equals("write")) {
			arg1.print(0);
			return new StrLit("");
		} else if (symbolName.equals("eval")) {
			return arg1;
		} else if (symbolName.equals("apply")) {
			return arg1.apply(arg2);
		} else if (symbolName.equals("read")) {
			Parser parser;
			parser = new Parser(new Scanner(System.in));
			Node a = parser.parseExp();
			return a;
		} else if (symbolName.equals("interaction-environment")) {
			interaction_environment.print(0);
		} else {
			// use "write" by default
			arg1.print(0);
			return new Nil();
		}
		return new StrLit(">");
	}

	public Node eval(Node t, Environment env) {
		return new Nil();
	}
}

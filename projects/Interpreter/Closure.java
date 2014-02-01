class Closure extends Node {

	private Node function;
	private Environment env;

	public Closure(Node f, Environment e) {
		function = f;
		env = e;
	}

	public Node getFun() {
		return function;
	}

	public Environment getEnv() {
		return env;
	}

	public boolean isProcedure() {
		return true;
	}

	public void print(int n) {
		Printer.indent(n);
		System.out.println("#{Procedure");
		function.print(n + 3);
		for (int i = 0; i < n; i++) {
			System.out.print(" ");
		}
		System.out.println('}');
	}

	public Node apply(Node args) {
		Environment e = this.getEnv();
		Node function = getFun();
		Node sym = function.getCar();
		function = function.getCdr().getCar();

		while (args != null && !args.getCar().isNull()) {
			e.define(sym.getCar(), args.getCar());
			sym = sym.getCdr();
			args = args.getCdr();
		}

		return function.eval(e);
	}

	public Node eval(Node t, Environment env) {
		return new Nil();
	}
}

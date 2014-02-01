class Cons extends Node {

	private Node car;
	private Node cdr;
	private Special form;
	private Ident strCar;

	void parseList() {

		if (!car.isSymbol()) {
			form = new Regular(this);
		} else {
			setStrCar((Ident) car);
			String sym = getStrCar().getName();

			if (sym.equalsIgnoreCase("quote")) {
				form = new Quote(this);
			} else if (sym.equalsIgnoreCase("lambda")) {
				form = new Lambda(this);
			} else if (sym.equalsIgnoreCase("begin")) {
				form = new Begin(this);
			} else if (sym.equalsIgnoreCase("if")) {
				form = new If(this);
			} else if (sym.equalsIgnoreCase("let")) {
				form = new Let(this);
			} else if (sym.equalsIgnoreCase("cond")) {
				form = new Cond(this);
			} else if (sym.equalsIgnoreCase("define")) {
				form = new Define(this);
			} else if (sym.equalsIgnoreCase("set!")) {
				form = new Set(this);
			} else {
				form = new Regular(this);
			}
		}
	}

	public Cons(Node a, Node d) {
		car = a;
		cdr = d;

		parseList();
	}

	public void print(int n) {
		form.print(this, n, false);
	}

	public void print(int n, boolean p) {
		form.print(this, n, p);
	}

	public boolean isPair() {
		return true;
	}

	public void setCar(Node a) {
		car = a;
		parseList();
	}

	public void setCdr(Node d) {
		cdr = d;
		parseList();
	}

	public Node getCar() {
		return car;
	}

	public Node getCdr() {
		return cdr;
	}

	public Node eval(Environment env) {
		return form.eval(this, env);
	}

	public Ident getStrCar() {
		return strCar;
	}

	public void setStrCar(Ident strCar) {
		this.strCar = strCar;
	}
}

class Node {
	private Ident id;

	public void print(int n) {
	}

	public void print(int n, boolean p) {
		print(n);
	}

	public boolean isBoolean() {
		return false;
	}

	public boolean isNumber() {
		return false;
	}

	public boolean isString() {
		return false;
	}

	public boolean isSymbol() {
		return false;
	}

	public boolean isNull() {
		return false;
	}

	public boolean isPair() {
		return false;
	}

	public static void print(Node t, int n, boolean p) {
		t.print(n, p);
	}

	public static Node getCar(Node t) {
		return t.getCar();
	}

	public static Node getCdr(Node t) {
		return t.getCdr();
	}

	public static boolean isNull(Node t) {
		return t.isNull();
	}

	public static boolean isPair(Node t) {
		return t.isPair();
	}

	public Node getCar() {
		return null;
	}

	public Node getCdr() {
		return null;
	}

	public void setCar(Node a) {
	}

	public void setCdr(Node d) {
	}

	public String getName() {
		return "";
	}

	public int getVal() {
		return 0;
	}

	public String getStrVal() {
		return "";
	}

	public boolean isProcedure() {
		return false;
	}

	public boolean getBoolean() {
		return false;
	}

	public Node apply(Node arg2) {
		return new Nil();
	}

	public Node eval(Environment env) {
		if (this instanceof Ident) {
			id = new Ident(this.getName());
			return id.eval(this, env);
		}
		return this;
	}
}
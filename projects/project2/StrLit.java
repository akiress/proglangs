class StrLit extends Node {

	private String strVal;
	private boolean printQuotes;

	public StrLit(String s) {
		strVal = s;
	}

	public StrLit(String string, boolean b) {
		strVal = string;
		printQuotes = b;
	}

	public void print(int n) {
		if (!printQuotes) {
			System.out.println(strVal);
			return;
		}
		Printer.printStrLit(n, strVal);
	}

	public boolean isString() {
		return true;
	}

	public String getStrVal() {
		return strVal;
	}

	public Node eval(Node t, Environment env) {
		return this;
	}
}

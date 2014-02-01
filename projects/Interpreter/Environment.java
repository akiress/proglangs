import java.util.ArrayList;

class Environment extends Node {
	public static ArrayList<String> errorMessages = new ArrayList<String>();

	private Node scope;
	private Environment env;

	public Environment() {
		scope = new Nil();
		env = null;
	}

	public Environment(Environment e) {
		scope = new Nil();
		env = e;
	}

	public void print(int n) {
		Printer.indent(n);
		System.out.println("#{Environment");
		scope.print(n + 3);
		if (env != null) {
			env.print(n + 3);
		}
		for (int i = 0; i < n; i++) {
			System.out.print(' ');
		}
		System.out.println('}');
	}

	// This is not in an object-oriented style, it's more or less a
	// translation of the Scheme assq function.
	private static Node find(Node id, Node alist) {
		if (!alist.isPair())
			return null;
		else {
			Node bind = alist.getCar();
			if (id.getName().equals(bind.getCar().getName()))
				return bind.getCdr();
			else
				return find(id, alist.getCdr());
		}
	}

	public Node lookup(Node id) {
		Node val = find(id, scope);
		if (val == null && env == null) {
			String err = "Error: undefined variable";
			getErrorMessages().add(err);
			// System.out.println(errorMessages.size());
			// System.out.println("hello");
			return null;
		} else if (val == null) {
			return env.lookup(id);
		} else {
			return val.getCar();
		}
	}

	public void define(Node id, Node val) {
		Node value = find(id, scope);
		if (value == null) {
			scope = new Cons(new Cons(id, new Cons(val, new Nil())), scope);
		} else {
			value.setCar(val);
		}
	}

	public void assign(Node id, Node val) {
		Node value = find(id, scope);
		if (value == null && env == null) {
			String err = "Error: undefined variable";
			getErrorMessages().add(err);
			// System.out.println(errorMessages.size());
			// System.out.println("hello");
		} else if (value == null) {
			assign(id, env);
		} else {
			setCar(new Cons(id, val));
		}
	}

	public Node eval(Node t, Environment env) {
		return new Nil();
	}

	public void resetErrorMsgs() {
		getErrorMessages().clear();
	}

	public ArrayList<String> getErrorMessages() {
		return errorMessages;
	}

	public void setErrorMessages(ArrayList<String> errorMessages) {
		this.errorMessages = errorMessages;
	}
}

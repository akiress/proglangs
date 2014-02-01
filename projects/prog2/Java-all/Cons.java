class Cons extends Node {
    private Node car;
    private Node cdr;
    private Special form;
    private Ident strCar;
    
    // parseList() `parses' special forms, constructs an appropriate
    // object of a subclass of Special, and stores a pointer to that
    // object in variable form.  It would be possible to fully parse
    // special forms at this point.  Since this causes complications
    // when using (incorrect) programs as data, it is easiest to let
    // parseList only look at the car for selecting the appropriate
    // object from the Special hierarchy and to leave the rest of
    // parsing up to the interpreter.
    void parseList() {
    	
    	  if (! car.isSymbol())
    	    form = new Regular(this);
    	  else {
    	  	strCar=(Ident)car;
    	  	String sym=strCar.getName();

    	    if (sym.equalsIgnoreCase("quote"))
    	      form = new Quote(this);
    	    else if (sym.equalsIgnoreCase("lambda"))
    	      form = new Lambda(this);
    	    else if (sym.equalsIgnoreCase("begin"))
    	      form = new Begin(this);
    	    else if (sym.equalsIgnoreCase("if"))
    	      form = new If(this);
    	    else if (sym.equalsIgnoreCase("let"))
    	      form = new Let(this);
    	    else if (sym.equalsIgnoreCase("cond"))
    	      form = new Cond(this);
    	    else if (sym.equalsIgnoreCase("define"))
    	      form = new Define(this);
    	    else if (sym.equalsIgnoreCase("set!"))
    	      form = new Set(this);
    	    else
    	      form = new Regular(this);
    	  }
    }
 
    	
    // TODO: Add any helper functions for parseList as appropriate.

    public Cons(Node a, Node d) {
	car = a;
	cdr = d;

	parseList();
    }

    public void print(int n) {form.print(this, n, false);}	
    public void print(int n, boolean p) {form.print(this, n, p);}
    public boolean isPair()   { return true; }

    public void setCar(Node a) {car=a; parseList();}
    public void setCdr(Node d) {cdr=d; parseList();}
    
    public Node getCar(){return car;}
    public Node getCdr(){return cdr;}

}

/* 
 * @authors Ben Guitreau and Morgan Hargrove
 * @date 08 Oct. 2013
 * Course: CSC 4101, Sec. 1
 * Instructor: Gerald Baumgartner
 * Project 1: Scheme Pretty-Printer
 */

// Cons.java

class Cons extends Node 
{
    private Node car;
    private Node cdr;
    private Special form;
  
    // parseList() `parses' special forms, constructs an appropriate
    // object of a subclass of Special, and stores a pointer to that
    // object in variable form.  It would be possible to fully parse
    // special forms at this point.  Since this causes complications
    // when using (incorrect) programs as data, it is easiest to let
    // parseList only look at the car for selecting the appropriate
    // object from the Special hierarchy and to leave the rest of
    // parsing up to the interpreter.
    void parseList() 
    { 
        if (car instanceof Ident) 
        {
            Ident ident = (Ident)car;
            String identName = ident.getIdentName();
            if (identName.equals("quote") || identName.equals("'")) 
            {
				form = new Quote((Cons)cdr);
			} 
            else if (identName.equals("lambda")) 
            {
				form = new Lambda();
			} 
            else if (identName.equals("begin")) 
            {
				form = new Begin();
			} 
            else if (identName.equals("if")) 
            {
				form = new If();
			} 
            else if (identName.equals("let")) 
            {
				form = new Let();
			} 
            else if (identName.equals("cond")) 
            {
				form = new Cond();
			} 
            else if (identName.equals("define")) 
            {
				form = new Define();
			} 
            else if (identName.equals("set!")) 
            {
				form = new Set(this);
			} 
            else 
            {
				form = new Regular(this);
			}
        } 
        else if (car instanceof IntLit) 
        {
            form = new Regular(this);
        } 
        else if (car instanceof StrLit) 
        {
            form = new Regular(this);
        } 
        else if (car instanceof BooleanLit) 
        {
            form = new Regular(this);
        } 
        else if (car instanceof Nil) 
        {
            form = new Regular(this);
        } 
        else 
        {
            form = new Regular(this);
        }
    }

    public Cons(Node a, Node d) 
    {
        car = a;
        cdr = d;
        parseList();
    }

    void print(int n) 
    {
        form.print(this, n, false);
    }

    void print(int n, boolean p) 
    {
        form.print(this, n, p);
    }
    
    @Override
	public boolean isPair() 
    {
		return car != null && cdr != null;
	}
    
	@Override
	public Node getCar()
    {
		return car;
	}
	
    @Override
	public Node getCdr()
    {
		return cdr;
	}
	
    @Override
	public void setCar(Node a)
    {
		car = a; //had 2 ; "a;;"
	}
	
    @Override
	public void setCdr(Node d)
    {
		car = d;
	}
	
    public void printQuote(int n, boolean p)
    {
		form.printQuote(this, n, p);
	}
}
/* 
 * @authors Ben Guitreau and Morgan Hargrove
 * @date 08 Oct. 2013
 * Course: CSC 4101, Sec. 1
 * Instructor: Gerald Baumgartner
 * Project 1: Scheme Pretty-Printer
 */

// Regular.java

import java.io.*;

class Regular extends Special 
{
    private Cons cons;
    
    public Regular(Cons cons)
    {
        this.cons = cons;
    }
    
    void print(Node t, int n, boolean p) 
    {
        if(!p)
    		System.out.print("(");
    	if (cons.getCar() instanceof Cons || cons.getCar() instanceof Nil) 
        {
			cons.getCar().print(n, false);
    	}
    	else 
        { 
    		cons.getCar().print(n, true);
    	}
    	if (cons.getCdr() != null)
			System.out.print(" ");
    	if (cons.getCdr() != null) 
        {
			cons.getCdr().print(n, true);
		}
		else 
        {
			System.out.print(")");		
		}
    }
    
    public void printQuote(Node t, int n, boolean p)
    {
    	print(t, n, p);
    }
    
    public Cons getCons()
    {
    	return cons;
    }
}
/* 
 * @authors Ben Guitreau and Morgan Hargrove
 * @date 08 Oct. 2013
 * Course: CSC 4101, Sec. 1
 * Instructor: Gerald Baumgartner
 * Project 1: Scheme Pretty-Printer
 */

// Set.java

import java.io.*;

class Set extends Special 
{
    private Cons cons;
    
    public Set(Cons cons)
    {
        this.cons = cons;
    }
    
    void print(Node t, int n, boolean p) 
    {
        if(!p)
    		System.out.print("(");
    	
    	if (cons.getCar() instanceof Cons) 
        {
			cons.getCar().print(n, false);	
    	}
    	else 
        { 
    		cons.getCar().print(n, false);
    	}
    	if (cons.getCdr() != null)
			System.out.print(" ");    	
    	if (cons.getCdr() != null) 
        {
    		cons.getCdr().print(n, true);
		}
		else 
        {
			System.out.println(")");		
		}
    }
    
    void printQuote(Node t, int n, boolean p)
    {
        print(t, n, p);
    }
}
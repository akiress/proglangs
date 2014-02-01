/* 
 * @authors Ben Guitreau and Morgan Hargrove
 * @date 08 Oct. 2013
 * Course: CSC 4101, Sec. 1
 * Instructor: Gerald Baumgartner
 * Project 1: Scheme Pretty-Printer
 */

// Lambda.java

import java.io.*;

class Lambda extends Special 
{
    public Lambda()
    {
    }
    
    void print(Node t, int n, boolean p) 
    {
        if(!p)
    		System.out.print("(");
    	System.out.print("lambda ");
    	if(t.getCdr() != null)
        {
    		t.getCdr().getCar().print(n+4, false);
    		System.out.println();
    		if(t.getCdr().getCdr() != null)
        		printElements((Cons)t.getCdr().getCdr(), n+4, false);
    	}
    	for(int i = 0; i < n; i++)
        {
    		System.out.print(" ");
    	}
        System.out.print(")");
    }
    
    void printElements(Cons t, int n, boolean isQuote) 
    {
    	if (isQuote) 
        {
    		System.out.print(" ");
    		t.getCar().printQuote(n, false);
    	} 
        else 
        {
			for(int i = 0; i < n; i++)
            {
        		System.out.print(" ");
    		}
    		t.getCar().print(n);
    		System.out.println();
    	}    	
    	if(t.getCdr() != null)
    		printElements((Cons)t.getCdr(), n, isQuote);
    }

	@Override
	void printQuote(Node t, int n, boolean p) 
    {
		if(!p)
			System.out.print("(");
		System.out.print("lambda");
		if(t.getCdr() != null)
	    	printElements((Cons)t.getCdr(), 0, true);
		System.out.print(")");
	}
}
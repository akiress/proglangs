/* 
 * @authors Ben Guitreau and Morgan Hargrove
 * @date 08 Oct. 2013
 * Course: CSC 4101, Sec. 1
 * Instructor: Gerald Baumgartner
 * Project 1: Scheme Pretty-Printer
 */

// Quote.java

import java.io.*;

class Quote extends Special 
{
    private Cons list = null;
    
    public Quote(Cons list)
    {
        this.list = list;
    }
    
    void print(Node t, int n, boolean p) 
    {
        System.out.print("'");
        ((Cons)list.getCar()).printQuote(n, false);
    }
    
    @Override
    void printQuote(Node t, int n, boolean p)
    {
        print(t, n, p);
    }
}
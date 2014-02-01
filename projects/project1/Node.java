/* 
 * @authors Ben Guitreau and Morgan Hargrove
 * @date 08 Oct. 2013
 * Course: CSC 4101, Sec. 1
 * Instructor: Gerald Baumgartner
 * Project 1: Scheme Pretty-Printer
 */

// Node.java

import java.io.*;

class Node 
{
    // The argument of print(int) is the number of characters to indent.
    // Every subclass of Node must implement print(int).
    void print(int n) 
    {
    }

    // The first argument of print(int, boolean) is the number of characters
    // to indent.  It is interpreted the same as for print(int).
    // The second argument is only useful for lists (nodes of classes
    // Cons or Nil).  For all other subclasses of Node, the booleanean
    // argument is ignored.  Therefore, print(n,p) defaults to print(n)
    // for all classes other than Cons and Nil.
    // For classes Cons and Nil, print(n,TRUE) means that the open
    // parenthesis was printed already by the caller.
    // Only classes Cons and Nil override print(int,boolean).
    // For correctly indenting special forms, you might need to pass
    // additional information to print.  What additional information
    // you pass is up to you.  If you only need one more bit, you can
    // encode that in the sign bit of n.  If you need additional parameters,
    // make sure that you define the method print in all the appropriate
    // subclasses of Node as well.
    void print(int n, boolean p) 
    {
        print(n);
    }

    // For parsing Cons nodes, for printing trees, and later for
    // evaluating them, we need some helper functions that test
    // the type of a node and that extract some information.

    // BooleanLit
    public boolean isBoolean()   
    { 
        return false; 
    }  
    // IntLit
    public boolean isNumber() 
    { 
        return false; 
    }  
    // StringLit
    public boolean isString() 
    { 
        return false; 
    }  
    // Ident
    public boolean isSymbol() 
    { 
        return false; 
    }  
    // nil
    public boolean isNull()   
    { 
        return false; 
    }  
    // Cons
    public boolean isPair()   
    { 
        return false; 
    }  

    // Report an error in these default methods and implement them
    // in class Cons.  After setCar, a Cons cell needs to be `parsed' again
    // using parseList.
    public Node getCar() 
    {
        return null;
    }
  
    public Node getCdr() 
    {
        return null;
    }
  
    public void setCar(Node a) 
    {
    }
  
    public void setCdr(Node d) 
    {
    }
    
    public void printQuote(int n, boolean p)
    {
        print(n, p);
    }
}
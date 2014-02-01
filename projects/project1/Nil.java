/* 
 * @authors Ben Guitreau and Morgan Hargrove
 * @date 08 Oct. 2013
 * Course: CSC 4101, Sec. 1
 * Instructor: Gerald Baumgartner
 * Project 1: Scheme Pretty-Printer
 */

// Nil.java

import java.io.*;

class Nil extends Node 
{
    public Nil() 
    { 
    }

    public void print(int n)		
    { 
        print(n, false); 
    }
    
    public void print(int n, boolean p) 
    {
        if (p)
        {
            System.out.print(")");
        }
        else
        {
            System.out.print("()");
        }
    }
    
    @Override
    public boolean isNull()
    {
        return true;
    }
}
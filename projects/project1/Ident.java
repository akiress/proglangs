/* 
 * @authors Ben Guitreau and Morgan Hargrove
 * @date 08 Oct. 2013
 * Course: CSC 4101, Sec. 1
 * Instructor: Gerald Baumgartner
 * Project 1: Scheme Pretty-Printer
 */

// Ident.java

import java.io.*;

class Ident extends Node 
{
    private String name;

    public Ident(String n) 
    { 
        name = n; 
    }

    public void print(int n) 
    {
        System.out.print(name);
    }
    
    public void print(int n, boolean parenPrintedLast)
    {
        if(parenPrintedLast)
            print(n);
        else
            print(n);
    }
    
    public String getIdentName()
    {
        return name;
    }
    
    @Override
    public boolean isSymbol()
    {
        return true;
    }
}
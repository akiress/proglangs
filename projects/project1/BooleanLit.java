/* 
 * @authors Ben Guitreau and Morgan Hargrove
 * @date 08 Oct. 2013
 * Course: CSC 4101, Sec. 1
 * Instructor: Gerald Baumgartner
 * Project 1: Scheme Pretty-Printer
 */

// BooleanLit.java

import java.io.*;

class BooleanLit extends Node 
{
    private boolean booleanVal;

    public BooleanLit(boolean b) 
    {
        booleanVal = b;
    }

    public void print(int n) 
    {
        if (booleanVal) 
        {
          System.out.println("#t");
        } 
        else 
        {
          System.out.println("#f");
        }
    }
    
    public boolean getVal()
    {
        return booleanVal;
    }
        
    @Override
    public boolean isBoolean()
    {
        return true;
    }
}
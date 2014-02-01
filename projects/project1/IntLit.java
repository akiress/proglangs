/* 
 * @authors Ben Guitreau and Morgan Hargrove
 * @date 08 Oct. 2013
 * Course: CSC 4101, Sec. 1
 * Instructor: Gerald Baumgartner
 * Project 1: Scheme Pretty-Printer
 */

// IntLit.java

import java.io.*;

class IntLit extends Node 
{
    private int intVal;

    public IntLit(int i) 
    { 
        intVal = i; 
    }

    public void print(int n) 
    {
        System.out.print(intVal);
    }
    
    public int getVal()
    {
	  return intVal;
    }
  
    @Override
    public boolean isNumber()
    {
	  return true;
    }
}
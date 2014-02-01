/* 
 * @authors Ben Guitreau and Morgan Hargrove
 * @date 08 Oct. 2013
 * Course: CSC 4101, Sec. 1
 * Instructor: Gerald Baumgartner
 * Project 1: Scheme Pretty-Printer
 */

// StrLit.java

import java.io.*;

class StrLit extends Node 
{
    private String strVal;

    public StrLit(String s) 
    { 
        strVal = s; 
    }

    public void print(int n) 
    {
        System.out.print("\"" + strVal + "\"");
    }
    
    public String getVal()
    {
        return strVal;
    }
    
    @Override
    public boolean isString()
    {
        return true;
    }
}
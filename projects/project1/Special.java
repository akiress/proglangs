/* 
 * @authors Ben Guitreau and Morgan Hargrove
 * @date 08 Oct. 2013
 * Course: CSC 4101, Sec. 1
 * Instructor: Gerald Baumgartner
 * Project 1: Scheme Pretty-Printer
 */

// Special.h -- the parse tree node data structure for special forms

import java.io.*;

// There are several different approaches for how to implement the Special
// hierarchy.  We'll discuss some of them in class.  The easiest solution
// is to not add any fields and to use empty constructors.

abstract class Special 
{
    abstract void print(Node t, int n, boolean p);
    abstract void printQuote(Node t, int n, boolean p);
}


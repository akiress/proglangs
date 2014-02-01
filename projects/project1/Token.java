/* 
 * @authors Ben Guitreau and Morgan Hargrove
 * @date 08 Oct. 2013
 * Course: CSC 4101, Sec. 1
 * Instructor: Gerald Baumgartner
 * Project 1: Scheme Pretty-Printer
 */

// Token.java

class Token implements TokenType 
{
    private int tt;

    Token(int t) 
    {
        tt = t;
    }

    int getType() 
    {
        return tt;
    }

    int getIntVal() 
    { 
        return 0; 
    }
    
    String getStrVal() 
    { 
        return ""; 
    }
    
    String getName() 
    { 
        return ""; 
    }
}
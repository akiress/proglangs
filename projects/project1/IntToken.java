/* 
 * @authors Ben Guitreau and Morgan Hargrove
 * @date 08 Oct. 2013
 * Course: CSC 4101, Sec. 1
 * Instructor: Gerald Baumgartner
 * Project 1: Scheme Pretty-Printer
 */

// IntToken.java

class IntToken extends Token 
{
    private int intVal;

    public IntToken(int i) 
    {
        super(TokenType.INT);
        intVal = i;
    }
    
    int getIntVal() 
    {
        return intVal;
    }
}
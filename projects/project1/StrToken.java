/* 
 * @authors Ben Guitreau and Morgan Hargrove
 * @date 08 Oct. 2013
 * Course: CSC 4101, Sec. 1
 * Instructor: Gerald Baumgartner
 * Project 1: Scheme Pretty-Printer
 */

// StrToken.java

class StrToken extends Token 
{
    private String strVal;

    public StrToken(String s) 
    {
        super(TokenType.STRING);
        strVal = s;
    }

    String getStrVal() 
    {
        return strVal;
    }
}
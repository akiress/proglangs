/* 
 * @authors Ben Guitreau and Morgan Hargrove
 * @date 08 Oct. 2013
 * Course: CSC 4101, Sec. 1
 * Instructor: Gerald Baumgartner
 * Project 1: Scheme Pretty-Printer
 */

// IdentToken.java

class IdentToken extends Token 
{
    private String name;

    public IdentToken(String s) 
    {
        super(TokenType.IDENT);
        name = s;
    }

    String getName() 
    {
        return name;
    }
}
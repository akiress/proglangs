/* 
 * @authors Ben Guitreau and Morgan Hargrove
 * @date 08 Oct. 2013
 * Course: CSC 4101, Sec. 1
 * Instructor: Gerald Baumgartner
 * Project 1: Scheme Pretty-Printer
 */

// Parser.java -- the implementation of class Parser

// Defines
//
//   class Parser;
//
// Parses the language
//
//   exp  ->  ( rest
//         |  #f
//         |  #t
//         |  ' exp
//         |  integer_constant
//         |  string_constant
//         |  identifier
//    rest -> )
//         |  exp+ [. exp] )
//
// and builds a parse tree.  Lists of the form (rest) are further
// `parsed' into regular lists and special forms in the constructor
// for the parse tree node class Cons.  See Cons.parseList() for
// more information.
//
// The parser is implemented as an LL(0) recursive descent parser.
// I.e., parseExp() expects that the first token of an exp has not
// been read yet.  If parseRest() reads the first token of an exp
// before calling parseExp(), that token must be put back so that
// it can be reread by parseExp() or an alternative version of
// parseExp() must be called.
//
// If EOF is reached (i.e., if the scanner returns a NULL) token,
// the parser returns a NULL tree.  In case of a parse error, the
// parser discards the offending token (which probably was a DOT
// or an RPAREN) and attempts to continue parsing with the next token.

class Parser 
{
    private Scanner scanner;

    public Parser(Scanner s) 
    { 
        scanner = s; 
    }
  
    public Node parseExp() 
    {
        Node exp = null;
        Token token = scanner.getNextToken();
        // EOF
        if (token == null)
        {
            exp = null;
        } 
        else if (token.getType() == TokenType.LPAREN) 
        {
            exp = parseRest(true);
        } 
        else if (token.getType() == TokenType.FALSE) 
        {
            exp = new BooleanLit(false);
        } 
        else if (token.getType() == TokenType.TRUE) 
        {
            exp = new BooleanLit(true);
        } 
        else if (token.getType() == TokenType.QUOTE) 
        {
            exp = new Cons(new Ident("'"), new Cons(parseExp(), null));
        } 
        else if (token.getType() == TokenType.INT) 
        {
            exp = new IntLit(token.getIntVal());
        } 
        else if (token.getType() == TokenType.STRING) 
        {
            exp = new StrLit(token.getStrVal());
        } 
        else if (token.getType() == TokenType.IDENT) 
        {
            exp = new Ident(token.getName());
        } 
        else if (token.getType() == TokenType.RPAREN) 
        {
            System.out.println("Token Error: )");
            exp = parseExp();
        } 
        else if (token.getType() == TokenType.DOT) 
        {
            System.out.println("Token Error: .");
            exp = parseExp();
        } 
        // parsing error
        else 
        {
            System.out.println("Token Error Type: " + token.getType());
        }
        return exp;
    }
  
    protected Node parseRest(boolean first) 
    {
        Token token = scanner.getNextToken();
        Node exp = null;

        if (token == null) 
        {
            exp = null;
        } 
        else if (token.getType() == TokenType.RPAREN) 
        {
            if (first)
                return new Nil();
            else
                return null;
        } 
        else if (token.getType() == TokenType.DOT) 
        {
            // look ahead
            token = scanner.getNextToken();
            if (token.getType() != TokenType.RPAREN)
            {
                scanner.pushBackToStream(token);
                exp = new Cons(parseExp(), null);
            }
            else
            {
                System.out.println("Token Error: unexpected ')'");
                exp = parseExp();
            }
        } 
        // exp
        else 
        {
            scanner.pushBackToStream(token);
            exp = new Cons(parseExp(), parseRest(false));
        }
        return exp;
    }
}
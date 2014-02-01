// For backward compatibility with Java 1.4, we use explicit constant
// declarations.  Java 1.5 would now have an enum construct.

interface TokenType {
    public static final int QUOTE = 0;			// '
    public static final int LPAREN = 1;			// (
    public static final int RPAREN = 2;                 // )
    public static final int DOT = 3;			// .
    public static final int TRUE = 4;			// #t
    public static final int FALSE = 5;			// #f
    public static final int INT = 6;			// integer constant
    public static final int STRING = 7;			// string constant
    public static final int IDENT = 8;			// identifier
}

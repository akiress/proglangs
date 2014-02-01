/* 
 * @authors Ben Guitreau and Morgan Hargrove
 * @date 08 Oct. 2013
 * Course: CSC 4101, Sec. 1
 * Instructor: Gerald Baumgartner
 * Project 1: Scheme Pretty-Printer
 */

// Scanner.java -- the implementation of class Scanner

import java.io.*;

class Scanner 
{
    private PushbackInputStream in;
    private byte[] buf = new byte[1000];    
    private Token revert = null;

    public Scanner(InputStream i) 
    { 
        in = new PushbackInputStream(i); 
    }
    
    public void pushBackToStream(Token token) 
    {
        revert = token;
    }
    
    private boolean isWhiteSpace(char ch)
    {
		return ch == ' ' || ch == '\t' || ch == '\n' || ch == '\r' || ch == '\f';
	}
    
    private boolean isFullCharRange(char ch)
    {
		return (ch >= 'A' && ch <= 'Z') || (ch >= 'a' && ch <= 'z') || (ch >= '<' && ch <= '@' 
				|| ch == '!' ||ch == '%' ||ch == '&' ||ch == '*' ||ch == '+' ||ch == '-' ||ch == '.' ||ch == '/' 
				|| ch == ':' ||ch == '^' ||ch == '_' ||ch == '~' );
    }
    
    public Token getNextToken() 
    {
        if (revert != null)
        {
            Token tmp = revert;
            revert = null;
            return tmp;
        }        
        int bite = -1;
	
        // It would be more efficient if we'd maintain our own input buffer
        // and read characters out of that buffer, but reading individual
        // characters from the input stream is easier.
        try 
        {
            bite = in.read();
        } 
        catch (IOException e) 
        {
            System.err.println("We fail: " + e.getMessage());
        }

        char ch = (char)bite;
        
        // skip white space and comments 
        if (isWhiteSpace(ch))
        {
            return getNextToken();
        }
        if(ch == ';')
        {
            try
            {
				while(in.read() != '\n');
			}
            catch(IOException e)
            {
				System.err.println("We fail:" + e.getMessage());
				bite = -1; 
			}
			return getNextToken();
        }        
        if (bite == -1)
            return null;
	
        // Special characters
        if (ch == '\'')
            return new Token(Token.QUOTE);            
        else if (ch == '(')
            return new Token(Token.LPAREN);        
        else if (ch == ')')
            return new Token(Token.RPAREN);        
        else if (ch == '.')
            // We ignore the special identifier `...'.
            return new Token(Token.DOT);

        // Boolean constants
        else if (ch == '#') 
        {
            try 
            {
                bite = in.read();
            } 
            catch (IOException e) 
            {
                System.err.println("We fail: " + e.getMessage());
            }
            if (bite == -1) 
            {
                System.err.println("Unexpected EOF following #");
                return null;
            }
            ch = (char)bite;
            if (ch == 't')
                return new Token(Token.TRUE);
            else if (ch == 'f')
                return new Token(Token.FALSE);
            else 
            {
                System.err.println("Illegal character '" + (char)ch + "' following #");
                return getNextToken();
            }
        }

        // String constants
        else if (ch == '"') 
        {
            int i = 0;
            for(i = 0; i < buf.length; i++)
			{
				try
                {
					ch = (char)in.read();
					if(ch == '\\')
					{   
                        buf[i]= (byte)'\\';
						i++;
						ch = (char)in.read();
						buf[i]= (byte)ch;
						continue;
					}
					if(ch == '\"')
                    {
						break;
					}
					buf[i] = (byte)ch;   			   			
				}
				catch(IOException e)
                {
					System.err.println("We fail: " + e.getMessage());
				}
				catch(IllegalArgumentException e)
				{
					System.err.println("We fail: " + e.getMessage());
				}	
			}
			byte[] str = new byte[i];	
			for(int j = 0; j < i; j++)
			{
				str[j] = buf[j]; 
			}
			return new StrToken(new String(str));
        }

        // Integer constants
        else if (ch >= '0' && ch <= '9') 
        {
            int j = 0;
			try
            {	
				for(j = 0; j < buf.length; j++)
                {
					buf[j] = (byte)ch;
					bite = in.read();
					ch = (char)bite;
					if(ch >= '0' && ch <= '9')
						continue;
					else if(isWhiteSpace(ch) || !isFullCharRange(ch))
                    {
						in.unread((byte)ch);
						break;
					}
                    else 
                    {
						throw new IllegalArgumentException("invalid argument; expected integer, not \'" +ch +"\'");
					}
				}
			} 
            catch (IOException e)
            {
				System.out.println("We fail: ");
				e.printStackTrace();
			} 
            catch (IllegalArgumentException e)
            {
				System.out.println("We fail: " + e.getMessage());
			}			
			byte[] temp = new byte[j+1];			
			for(int i = 0; i <= j; i++)
            {
				temp[i]=buf[i];
			}
			int n = Integer.MAX_VALUE;
			try
            {
				n = Integer.parseInt(new String(temp));
			} 
            catch (NumberFormatException nf) 
            {
				System.out.println("Integer too large: "+ new String(temp)+"\n Reset to Integer.MAX_VALUE");
			}
			return new IntToken(n);
        }

        // Identifiers
        else if (isFullCharRange(ch))
        {
            try 
            {
				int i = 0;
				for (i = 0; i < buf.length; i++)
				{
					buf[i] = (byte)ch;
					bite = in.read();
					ch = (char)bite;
					if (isFullCharRange(ch) || (ch >= '0' && ch <= '9'))
						continue;
					// put char after ident back into input
                    else
					{
						in.unread((int)ch);
						break;
					}
				}
				byte[] id = new byte[i+1];	
				for(int j = 0; j <= i; j++)
				{
					id[j] = buf[j]; 
				}
				String identStr = new String(id);
				return new IdentToken(identStr.toLowerCase());
			}
			catch(IOException e) 
            {
				System.err.println("We fail:" + e.getMessage());    
				return getNextToken();
			}
        }

        // Illegal character
        else 
        {
            System.err.println("Illegal input character '" + (char) ch + '\'');
            return getNextToken();
        }
    };
}
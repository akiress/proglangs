   Authors: Ben Guitreau (cs410120) and Morgan Hargrove (cs410123)
      Date: 14 Nov. 2013
    Course: CSC 4101, Sec. 1
Instructor: Gerald Baumgartner
 Project 2: Scheme Interpreter

How to compile and run:
  (in the prog2 directory)
    $ javac *.java
    $ java Main
  OR
    $ build.sh  
  * build.sh will create and run interpreter.jar
    
    Once running, our interpreter should look like:
        Interpreter>
    Enter scheme commands to run tests.  The interpreter can be 
    closed using (quit), (exit), or Ctrl+C.
    
    Our interpreter can also be run using standard/file input, e.g.:
        $ java Main < test.scm
      OR
        $ build.sh < test.scm
    which will run the file's commands in the Interpreter and 
    return the output.
    
Design / Functionality:
    The basic foundation of our interpreter is fairly solid; however
    there are several things that are still not functioning 
    properly, e.g.:
    - (write) is evaluating when it should be just pretty-printing
      (and it also may not be returning the correct output)
    - (display) does the same thing as (write)
    - (interaction-environment) may not be returning the correct output
    - (cons) is missing a space in the print (e.g. (cons '(a b) '(1 2))
      will return '(a b1 2))
    - (list) and . are not properly implemented
    etc...

"prog2" should include:
    - README.txt
    - build.sh
    - manifest.txt
    - 29 Java source code files:
        Begin.java, BooleanLit.java, BuiltIn.java, Closure.java, 
        Cond.java, Cons.java, Define.java, Environment.java, 
        Ident.java, IdentToken.java, If.java, IntLit.java, 
        IntToken.java, Lambda.java, Let.java, Main.java, Nil.java, 
        Node.java, Parser.java, Printer.java, Quote.java, 
        Regular.java, Scanner.java, Set.java, Special.java, 
        StrLit.java, StrToken.java, Token.java, TokenType.java

// Main.cpp -- the main program
//
// Defines
//
//   int main(int, char**)

#include <iostream>

using namespace std;

#include "Tokens.h"
#include "Tree.h"
#include "Scanner.h"
#include "Parser.h"

// Array of token names used for debugging the scanner
static char * TokenName[] = {
  "QUOTE",				// '
  "LPAREN",				// (
  "RPAREN",				// )
  "DOT",				// .
  "TRUET",				// #t
  "FALSET",				// #f
  "INT",				// integer constant
  "STRING",				// string constant
  "IDENT"				// identifier
};


int main (int argc, char ** argv) {
  // create scanner that reads from standard input
  Scanner * scanner = new Scanner(&cin);

  if (argc > 2) {
    cerr << "Usage: " << argv[0] << "[-d]" << endl;
    return 1;
  }

  // if commandline option -d is provided, debug the scanner
  if (argc == 2 && argv[1][0] == '-' && argv[1][1] == 'd') {
    // debug scanner
    Token * tok = scanner->getNextToken();
    while (tok != NULL) {
      TokenType tt = tok->getType();
      cout << TokenName[tt];
      if (tt == INT)
	cout << ", intVal = " << tok->getIntVal();
      else if (tt == STRING)
	cout << ", strVal = " << tok->getStrVal();
      else if (tt == IDENT)
	cout << ", name = " << tok->getName();
      cout << endl;
      tok = scanner->getNextToken();
    }
  }

  // Create parser
  Parser * parser = new Parser(scanner);
  Node * root;

  // Parse and pretty-print each input expression
  root = parser->parseExp();
  while (root != NULL) {
    root->print(0);
    root = parser->parseExp();
  }

  return 0;
}

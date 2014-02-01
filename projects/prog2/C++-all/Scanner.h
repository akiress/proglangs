// Scanner.h -- the interface for class Scanner
//
// Defines
//
//   class Scanner;

#ifndef SCANNER_H
#define SCANNER_H

#include <iostream>

using namespace std;

#include "Tokens.h"

// maximum length of strings and identifiers
#define BUFSIZE 1000


class Scanner {
 private:
  istream * in;
  char buf[BUFSIZE];

 public:
  Scanner(istream * i)		{ in = i; }

  Token * getNextToken();
};

#endif

// Special.cpp -- the implementation of the special form classes

#include "Special.h"
#include "Printer.h"

// The print() methods for the Special node hierarchy were moved
// to file Special-print.cpp.  You can add your own code to the
// Special node hierarchy in this file and simply link in the
// compiled print() methods in Special-print.o by adding the file
// Special-print.o to the variable OBJ in the Makefile.

void
Quote::print(Node * t, int n, bool p) {
  Printer::printQuote(t, n, p);
}

void
Lambda::print(Node * t, int n, bool p) {
  Printer::printLambda(t, n, p);
}

void
Begin::print(Node * t, int n, bool p) {
  Printer::printBegin(t, n, p);
}

void
If::print(Node * t, int n, bool p) {
  Printer::printIf(t, n, p);
}

void
Let::print(Node * t, int n, bool p) {
  Printer::printLet(t, n, p);
}

void
Cond::print(Node * t, int n, bool p) {
  Printer::printCond(t, n, p);
}

void
Define::print(Node * t, int n, bool p) {
  Printer::printDefine(t, n, p);
}

void
Set::print(Node * t, int n, bool p) {
  Printer::printSet(t, n, p);
}

void
Regular::print(Node * t, int n, bool p) {
  Printer::printRegular(t, n, p);
}

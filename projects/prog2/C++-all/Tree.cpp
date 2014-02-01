// Tree.cpp -- the implementation of the tree classes

#include <string.h>
#include "Tree.h"
#include "Special.h"
#include "Printer.h"

// The static methods print, getCar, getCdr, isNull, and isPair are
// needed so that Printer.cpp does not have to be recompiled if
// virtual methods are added to class Node.  Do not call them in
// your code.

void Node::print(Node * t, int n, bool p) { t->print(n, p); }
Node * Node::getCar(Node * t) { return t->getCar(); }
Node * Node::getCdr(Node * t) { return t->getCdr(); }
bool Node::isNull(Node * t) { return t->isNull(); }
bool Node::isPair(Node * t) { return t->isPair(); }


// The print() methods for the parse tree node hierarchy were moved to
// file Printer.cpp.  You can add your own code to the parse tree node
// hierarchy in this file and simply link in the compiled print()
// methods in Printer.o by adding the file Printer.o to the variable
// OBJ in the Makefile.

void
BoolLit::print(int n) {
  Printer::printBoolLit(n, boolVal);
}


void
IntLit::print(int n) {
  Printer::printIntLit(n, intVal);
}


void
StrLit::print(int n) {
  Printer::printStrLit(n, strVal);
}


void
Ident::print(int n) {
  Printer::printIdent(n, name);
}


void
Nil::print(int n, bool p) {
  Printer::printNil(n, p);
}


void
Cons::print(int n) {
  form->print(this, n, FALSE);
}


void
Cons::print(int n, bool p) {
  form->print(this, n, p);
}


void
Cons::parseList() {
  if (! car->isSymbol())
    form = new Regular(this);
  else {
    char * sym = car->getName();
    if (strcmp(sym, "quote") == 0)
      form = new Quote(this);
    else if (strcmp(sym, "lambda") == 0)
      form = new Lambda(this);
    else if (strcmp(sym, "begin") == 0)
      form = new Begin(this);
    else if (strcmp(sym, "if") == 0)
      form = new If(this);
    else if (strcmp(sym, "let") == 0)
      form = new Let(this);
    else if (strcmp(sym, "cond") == 0)
      form = new Cond(this);
    else if (strcmp(sym, "define") == 0)
      form = new Define(this);
    else if (strcmp(sym, "set!") == 0)
      form = new Set(this);
    else
      form = new Regular(this);
  }
}

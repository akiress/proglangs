// Tree.h -- the parse tree node data structure
//
// Defines the types
//
//   class Node;
//   class BoolLit : public Node;
//   class IntLit : public Node;
//   class StrLit : public Node;
//   class Ident : public Node;
//   class Nil : public Node;
//   class Cons : public Node;

#ifndef TREE_H
#define TREE_H

#include <iostream>

#ifndef NULL
#define NULL 0
#endif
#define FALSE 0
#define TRUE  1

class Special;

class Node {
 public:
  // The static methods print, getCar, getCdr, isNull, and isPair are
  // needed so that Printer.cpp does not have to be recompiled if
  // virtual methods are added to class Node.  Do not call them in
  // your code.
  static void print(Node * t, int n, bool p);
  static Node * getCar(Node * t);
  static Node * getCdr(Node * t);
  static bool isNull(Node * t);
  static bool isPair(Node * t);

  // The argument of print(int) is the number of characters to indent.
  // Every subclass of Node must implement print(int).
  virtual void print(int n) = 0;

  // The first argument of print(int, bool) is the number of characters
  // to indent.  It is interpreted the same as for print(int).
  // The second argument is only useful for lists (nodes of classes
  // Cons or Nil).  For all other subclasses of Node, the boolean
  // argument is ignored.  Therefore, print(n,p) defaults to print(n)
  // for all classes other than Cons and Nil.
  // For classes Cons and Nil, print(n,TRUE) means that the open
  // parenthesis was printed already by the caller.
  // Only classes Cons and Nil override print(int,bool).
  virtual void print(int n, bool p) { print(n); }

  // For parsing Cons nodes, for printing trees, and later for
  // evaluating them, we need some helper functions that test
  // the type of a node and that extract some information.

  // these are implemented in the appropriate subclasses to return TRUE.
  virtual bool isBool()   { return FALSE; }  // BoolLit
  virtual bool isNumber() { return FALSE; }  // IntLit
  virtual bool isString() { return FALSE; }  // StringLit
  virtual bool isSymbol() { return FALSE; }  // Ident
  virtual bool isNull()   { return FALSE; }  // Nil
  virtual bool isPair()   { return FALSE; }  // Cons

  // TODO: Report an error in these default methods and implement them
  // in class Cons.  After setCar and setCdr, a Cons cell needs to
  // be parsed again.
  virtual Node * getCar() { return NULL; }
  virtual Node * getCdr() { return NULL; }
  virtual void setCar(Node * a) { }
  virtual void setCdr(Node * d) { }

  virtual char * getName() { return NULL; }
};


class BoolLit : public Node {
 private:
  bool boolVal;

 public:
  BoolLit(bool b) { boolVal = b;  }

  virtual bool isBool()   { return TRUE; }

  virtual void print(int n);
};


class IntLit : public Node {
 private:
  int intVal;

 public:
  IntLit(int i) { intVal = i; }

  virtual bool isNumber() { return TRUE; }

  virtual void print(int n);
};


class StrLit : public Node {
 private:
  char * strVal;

 public:
  StrLit(char * s) { strVal = s; }

  virtual bool isString() { return TRUE; }

  virtual void print(int n);
};


class Ident : public Node {
 private:
  char * name;

 public:
  Ident(char * n) { name = n; }

  virtual bool isSymbol() { return TRUE; }

  virtual char * getName() { return name; }

  virtual void print(int n);
};


class Nil : public Node {
 public:
  Nil() { }

  virtual bool isNull()   { return TRUE; }

  virtual void print(int n)		{ print(n, FALSE); }
  virtual void print(int n, bool p);
};


class Cons : public Node {
 private:
  Node * car;
  Node * cdr;
  Special * form;
  
  // parseList() parses special forms, constructs an appropriate
  // object of a subclass of Special, and stores a pointer to that
  // object in variable form.
  void parseList();
  // TODO: Add any helper functions for parseList as appropriate.

 public:
  Cons(Node * a, Node * d) { car = a;  cdr = d;  parseList();}

  virtual bool isPair()   { return TRUE; }

  virtual Node * getCar() { return car; }
  virtual Node * getCdr() { return cdr; }
  virtual void setCar(Node * a) { car = a;  parseList();}
  virtual void setCdr(Node * d) { cdr = d;  parseList();}

  virtual void print(int n);
  virtual void print(int n, bool p);
};

#endif

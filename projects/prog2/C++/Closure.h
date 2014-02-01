// Closure.h -- the data structure for function closures

#ifndef CLOSURE_H
#define CLOSURE_H

#include "Tree.h"
#include "Environment.h"


// Class BuiltIn is used for representing the value of built-in functions
// such as +.  Populate the initial environment with
// (name, new BuiltIn(name)) pairs.

// The object-oriented style for implementing built-in functions would be
// to include the C++ function for implementing a Scheme built-in in the
// BuiltIn object.  This could be done by writing one subclass of class
// BuiltIn for each built-in function and implementing the method apply
// appropriately.  This requires a large number of classes, though.
// Another alternative is to program BuiltIn::apply() in a functional
// style by writing a large if-then-else chain that tests the name of
// of the function symbol.

class BuiltIn : public Node {
  Node * symbol;

 public:
  BuiltIn(Node * s)		{ symbol = s; }

  Node * getSymbol()		{ return symbol; }

  // TODO: The virtual function isProcedure() should be defined in
  // class Node to return FALSE.
  virtual bool isProcedure()	{ return TRUE; }

  virtual void print(int n);

  // TODO: The virtual function apply() should be defined in class Node
  // to report an error.  It should be overwritten only in classes
  // BuiltIn and Closure.
  virtual Node * apply (Node * args);
};
  

// Class Closure is used to represent the value of lambda expressions.
// It consists of the lambda expression itself, together with the
// environment in which the lambda expression was evaluated.

// The virtual function apply() takes the environment out of the closure,
// adds a new frame for the function call, defines bindings for the
// parameters with the argument values in the new frame, and evaluates
// the function body.

class Closure : public Node {
  Node * fun;			// a lambda expression
  Environment * env;		// the environment in which the function
				// was defined
 public:
  Closure(Node * f, Environment * e)	{ fun = f;  env = e; }

  Node * getFun()		{ return fun; }
  Environment * getEnv()	{ return env; }

  virtual bool isProcedure()	{ return TRUE; }

  virtual void print(int n);

  virtual Node * apply (Node * args);
};

#endif

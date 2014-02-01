// Environment.h -- a data structure for Scheme environments

#ifndef ENVIRONMENT_H
#define ENVIRONMENT_H

#include "Tree.h"

// An Environment is a list of frames.  Each frame represents a scope
// in the program and contains a set of name-value pairs.  The first
// frame in the environment represents the innermost scope.

// For the code below, I am assuming that a scope is implemented
// as an association list, i.e., a list of two-element lists.  E.g.,
// the association list ((x 1) (y 2)) associates the value 1 with x
// and the value 2 with y.

// To implement environments in an object-oriented style, it would
// be better to define a Frame class and make an Environment a list
// of such Frame objects.  If we simply use the parse tree structure
// for lists of association lists, we end up having to write the
// lookup functions in a more functional style.

// You need the following methods for modifying environments:
//  - constructors:
//      - create the empty environment (an environment with an empty frame)
//      - add an empty frame to the front of an existing environment
//  - lookup the value for a name (for implementing variable lookup)
//      if the name exists in the innermost scope, return the value
//      if it doesn't exist, look it up in the enclosing scope
//      if we don't find the name, it is an error
//  - define a name (for implementing define and parameter passing)
//      if the name already exists in the innermost scope, update the value
//      otherwise add a name-value pair as first element to the innermost scope
//  - assign to a name (for implementing set!)
//      if the name exists in the innermost scope, update the value
//      if it doesn't exist, perform the assignment in the enclosing scope
//      if we don't find the name, it is an error

class Environment : public Node {

  // An Environment is implemented like a Cons node, in which
  // every list element (every frame) is an association list.
  // Instead of Nil(), we use NULL to terminate the list.

  Node * scope;			// the innermost scope, an association list
  Environment * env;		// the enclosing environment

 public:
  Environment()			{ scope = new Nil(); env = NULL; }
  Environment(Environment * e)	{ scope = new Nil(); env = e; }

  virtual void print(int n);

  Node * lookup (Node * id);
  void define (Node * id, Node * val);
  void assign (Node * id, Node * val);
};

#endif

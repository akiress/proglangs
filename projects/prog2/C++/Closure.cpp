// Closure.cpp -- the data structure for function closures

#include <iostream>
#include "Closure.h"

using namespace std;

void
BuiltIn::print(int n) {
  // there got to be a more efficient way to print n spaces
  for (int i = 0; i < n; i++)
    cout << ' ';
  cout << "#{Built-in Procedure" << endl;
  symbol->print(n+3);
  for (int i = 0; i < n; i++)
    cout << ' ';
  cout << '}' << endl;
}


Node *
BuiltIn::apply(Node * args) {
  // TODO: implement this, see the comment in Closure.h
  return NULL;
}


void
Closure::print(int n) {
  // there got to be a more efficient way to print n spaces
  for (int i = 0; i < n; i++)
    cout << ' ';
  cout << "#{Procedure" << endl;
  fun->print(n+3);
  for (int i = 0; i < n; i++)
    cout << ' ';
  cout << '}' << endl;
}


Node *
Closure::apply(Node * args) {
  // TODO: implement this, see the comment in Closure.h
  return NULL;
}

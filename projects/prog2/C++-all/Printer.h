// Printer.h -- the interface for class Parser

#ifndef PRINTER_H
#define PRINTER_H

class Node;

class Printer {
 private:
  static void indent(int & n);
 public:
  static void printBoolLit(int n, int boolVal);
  static void printIntLit(int n, int intVal);
  static void printStrLit(int n, char * strVal);
  static void printIdent(int n, char * name);
  static void printNil(int n, bool p);
  static void printQuote(Node * t, int n, bool p);
  static void printLambda(Node * t, int n, bool p);
  static void printBegin(Node * t, int n, bool p);
  static void printIf(Node * t, int n, bool p);
  static void printLet(Node * t, int n, bool p);
  static void printCond(Node * t, int n, bool p);
  static void printDefine(Node * t, int n, bool p);
  static void printSet(Node * t, int n, bool p);
  static void printRegular(Node * t, int n, bool p);
};

#endif

// Tokens.h -- the token data structure
//
// Defines the types
//
//   enum  TokenType;
//   class Token;
//   class IntToken : public Token;
//   class StrToken : public Token;
//   class IdentToken : public Token;

#ifndef TOKENS_H
#define TOKENS_H

#include <string.h>

#ifndef NULL
#define NULL 0
#endif


enum TokenType {
  QUOTE,				// '
  LPAREN,				// (
  RPAREN,				// )
  DOT,					// .
  TRUET,				// #t
  FALSET,				// #f
  INT,					// integer constant
  STRING,				// string constant
  IDENT					// identifier
};


class Token {
 private:
  TokenType tt;

 public:
  Token(TokenType t)			{ tt = t; }

  TokenType	 getType()		{ return tt; }

  virtual int    getIntVal()		{ return 0; }
  virtual char * getStrVal()		{ return NULL; }
  virtual char * getName()		{ return NULL; }
};


class IntToken : public Token {
 private:
  int intVal;

 public:
  IntToken(int i) : Token(INT)		{ intVal = i; }

  virtual int    getIntVal()		{ return intVal; }
};


class StrToken : public Token {
 private:
  char * strVal;

 public:
  StrToken(char * s) : Token(STRING)	{ strVal = strdup(s); }

  virtual char * getStrVal()		{ return strVal; }
};


class IdentToken : public Token {
 private:
  char * name;

 public:
  IdentToken(char * s) : Token(IDENT)	{ name = strdup(s); }

  virtual char * getName()		{ return name; }
};

#endif

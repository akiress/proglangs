#
# Makefile for CSC 4101
#
# This Makefile is written for GNU Make
#
# Maintain the following definitions:
#
# HDR       all header files (*.h) that you create
# SRC       all C++ source files (*.cpp) that you create
# OBJ       all object files (*.o) required to load your program
# EXE       the name of the executable
# PRT       your favorite printer
# GRD       all files required for grading
#
# Use the following make targets:
#
# all       (the default) to build your program (into ${EXE})
# clean     to remove unnecessary files
# veryclean to remove eveything but source files
# list      to list files changed since the last listing
# listall   to list your complete program
# submit    to hand in your program for grading
# depend    to update header file dependencies

HDR  =	Tokens.h Scanner.h Parser.h Tree.h Special.h \
	Closure.h Environment.h
SRC  =	Main.cpp Scanner.cpp Parser.cpp Tree.cpp Special.cpp \
	Closure.cpp Environment.cpp
OBJ  =	Main.o Scanner.o Parser.o Tree.o Printer.o Special.o \
	Closure.o Environment.o
EXE  =	scheme4101
PRT  =	lp2
GRD  =	README Makefile ${HDR} ${SRC}

DEP =	${SRC}
CFLAGS= -g

# Use g++ as C compiler as well
CC =	g++
CPP =	g++

.PHONY: all clean veryclean listall submit depend

all: ${EXE}

${EXE}: ${OBJ}
	${CPP} ${CFLAGS} -o ${EXE} ${OBJ}

clean:
	rm -f ${OBJ} core

veryclean:
	rm -f ${OBJ} core
	rm -f Makefile.bak ${EXE} list *~

list: ${GRD}
	@lp -d ${PRT} -o duplex ${GRD}
	@echo "Update: `date`" >> list

listall:
	@lp -d ${PRT} -o duplex ${GRD}
	@echo "Complete: `date`" >> list

submit:
	make veryclean
	/classes/cs4101/cs4101a/bin/r_copy 2

.SUFFIXES: .cpp

%.o: %.cpp
	${CPP} ${CFLAGS} -c -o $*.o $*.cpp

depend: ${SRC} ${SRCg}
	cp -p Makefile Makefile.bak
	awk '{ print $0; } /(make) (depend)/ { exit; }' Makefile.bak > Makefile
	echo "" >> Makefile
	${CPP} ${CFLAGS} -MM ${SRC} ${SRCg} >> Makefile
	echo "" >> Makefile
	echo "# *** Do not add anything here - It will go away. ***" >>Makefile

# DO NOT DELETE THIS LINE - make depend DEPENDS ON IT

Main.o: Main.cpp Tokens.h Tree.h Scanner.h Parser.h
Scanner.o: Scanner.cpp Scanner.h Tokens.h
Parser.o: Parser.cpp Parser.h Tokens.h Tree.h Scanner.h
Tree.o: Tree.cpp Tree.h

# *** Do not add anything here - It will go away. ***

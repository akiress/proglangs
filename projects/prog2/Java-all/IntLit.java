import java.io.*;
class IntLit extends Node {
  private int intVal;

  public IntLit(int i) { intVal = i; }

  public void print(int n) {
  	Printer.printIntLit(n, intVal);
  }

  public boolean isNumber() { return true; }

}

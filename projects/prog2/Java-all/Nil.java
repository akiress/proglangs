import java.io.*;
class Nil extends Node {
	
  private static Nil inst=null;
  
  protected Nil() { }
  
  public static Nil getInstance(){
  
  		if(inst==null)
  			inst=new Nil();
  		
  		return inst;
  	
}

  public void print(int n)		{ print(n, false); }
  public void print(int n, boolean p) {
  	 Printer.printNil(n, p);
  }
  
  public boolean isNull()   { return true; }
  
  
}

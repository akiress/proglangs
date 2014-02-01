javac -g *.java
jar cvfm interpreter.jar manifest.txt *
java -jar interpreter.jar
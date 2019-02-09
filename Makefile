default: Board

%: %.java
	javac $@.java
	java -ea $@

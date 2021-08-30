SRCDIR = src
BINDIR = bin
DOCDIR = docs
JAVAFILES = src/HashEntry.java src/HashTable.java src/Linear.java src/Quadratic.java src/Chaining.java src/Main.java

JC = javac
JFLAGS = -g -d $(BINDIR) -cp $(BINDIR)

vpath %.java $(SRCDIR)
vpath %.class $(BINDIR)

.SUFFIXES: .java .class

.java.class:
	$(JC) $(JFLAGS) $<

all: HashEntry.class HashTable.class Linear.class Quadratic.class Chaining.class Main.class doc

.PHONY: doc

doc:
	@javadoc -d doc $(JAVAFILES)

cleandoc:
	@rm -rf| $(DOCDIR)
	@mkdir $(DOCDIR)

clean:
	@rm -f $(BINDIR)/*.class

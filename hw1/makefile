TESTFILE = RCV1.very_small_test.txt
TRAINFILE = RCV1.very_small_train.txt

JFLAGS = -g
JC = javac
RM = rm -f
CAT = cat
.SUFFIXES: .java .class

all:

demo:
	@$(RM) *.class
	@$(JC) $(JFLAGS) NBUtils.java
	@$(JC) $(JFLAGS) NBTrain.java
	@$(JC) $(JFLAGS) NBTest.java
	@$(CAT) RCV1.very_small_train.txt | java NBTrain | java NBTest -t RCV1.very_small_test.txt
	@$(RM) *.class

test:
	@$(RM) *.class
	@$(JC) $(JFLAGS) NBUtils.java
	@$(JC) $(JFLAGS) NBTrain.java
	@$(JC) $(JFLAGS) NBTest.java
	@$(CAT) RCV1.very_small_train.txt | java NBTrain | java NBTest -t $(TESTFILE)
	@$(RM) *.class

asdf:
	@$(RM) *.class
	@$(JC) $(JFLAGS) NBUtils.java
	@$(JC) $(JFLAGS) NBTrain.java
	@$(JC) $(JFLAGS) NBTest.java
	@$(CAT) $(TRAINFILE) | java NBTrain | java NBTest -t $(TESTFILE)
	@$(RM) *.class

	






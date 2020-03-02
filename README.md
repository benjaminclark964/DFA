Project 1 Part 1
Authors: Kyle Tupper & Benjamin Clark

Overview:<br/>
This project is an implementation of a deterministic finite automaton.

Compiling and Using: <br/>
As stated in the assignment:
To compile fa.dfa.DFADriver from the top directory of the files:
$ javac fa/dfa/DFADriver.java
To run fa.dfa.DFADriver:
$ java fa.dfa.DFADriver ./tests/p1tc1.txt
There is also multiple testing documents available to run with DFADriver

Discussion:<br/>
We were given the general framework for this project through the .zip file. Started by creating the getState and addState methods in order to keep track of states inside of the DFA. We had to switch how we did some of the code, such sets to HashLinkedSets, towards the end of the project in order to create that grid output in the toString because the Set addAll() method adds elements on top of one another.

Testing: <br/>
There was testing files given in order to test the project, we used those as a general benchmark until we were able to get the project working. Once those 3 files all produced correct outputs, we messed with some of the order of the letters being passed in, to make sure it could handle some different input.

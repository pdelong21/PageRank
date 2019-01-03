PATRICK DELONG cs435 2200 mp

OPTION 3 - PAGERANK
TO RUN PROGRAM DO THE FOLLOWING EXCLUDING QUOTATIONS...

1)  COMPILE JAVA PROGRAM IN TERMINAL TYPE "javac PageRank_2200.java"
2)  RUN THE PROGRAM USING "java PageRank_2200 ARG1 ARG2 ARG3" WHERE
    - ARG1 is either 0 for running until we achieve the fixed errorate
      OR ANY POSITIVE INTEGER to run THAT amount of iterations.
    - ARG2 is going to be the initializer for the ranks of each vertex, you can use the following values:
      a) "-2" -> this will make each vertex initial rank equal to 1/sqrt(n), where n is the amount of vertices
      b) "-1" -> this will make each vertex initial rank equal to 1/n, where n is the amount of vertices
      c) "0"  -> this will make each vertex initial rank equal to 0
      d) "1"  -> this will make each vertex initial rank equal to 1
    - ARG3 is the FILENAME to be opened and parsed for the vertices and edges and it should be in this format:
      ex: of FILENAME - FIRST LINE IS THE AMOUINT OF VERTICES AND EDGES

         4 4
         0 2
         0 3
         1 0
         2 1

DEBUG REPORT FOR PAGERANK:
    I did put a first if statement in PageRank_2200.java that checks for the right amount of arguments in case the user
    gave more or less than the amount of arguments it should have. If they didn't give 3 ARGS than it returns with a
    message of how to type on the command line to get it to run.

    One thing that WILL CRASH MY PROGRAM is if you have a blank line on the top of your text file or after the last line
    because then it will not make the AdjacencyList correctly and WILL CRASH.

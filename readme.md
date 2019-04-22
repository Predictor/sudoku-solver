# [Sudoku](https://en.wikipedia.org/wiki/Sudoku) solver
## Prerequisites 
- [__JDK 12__](https://www.oracle.com/technetwork/java/javase/downloads/jdk12-downloads-5295953.html) (lower versions might work)
- [__IntelliJ IDEA__](https://download.jetbrains.com/idea/ideaIU-2019.1.exe) (to build and run tests, as there is no build script provided)
## Input file format
[Plain text file](https://github.com/Predictor/sudoku-solver/blob/master/test/resources/sample1.txt) containing 9 rows of 9 numbers (0-9). Each number represents a cell. 0 indicates an empty cell. The file can contain tabs or spaces or line breaks for readability. All those separators will be ignored.
## Command line arguments
The only required and allowed argument is absolute or relative path to an input file. See [SudokuSolverArgs.java](https://github.com/Predictor/sudoku-solver/blob/master/src/com/predictor/sudoku/solver/SudokuSolverArgs.java).
## Main classes
- [__SudokuSolverApp__](https://github.com/Predictor/sudoku-solver/blob/master/src/com/predictor/sudoku/solver/SudokuSolverApp.java) – contains main method of the application
- [__SudokuSolverArgs__](https://github.com/Predictor/sudoku-solver/blob/master/src/com/predictor/sudoku/solver/SudokuSolverArgs.java) – command line arguments parser (check for input format)
- [__SudokuBoard__](https://github.com/Predictor/sudoku-solver/blob/master/src/com/predictor/sudoku/solver/SudokuBoard.java) – represents a Sudoku puzzle, allows getting a cell value, or setting a cell value, also provides mechanics to validate cell input values according to Sudoku rules.
- [__SudokuBacktrackingSolver__](https://github.com/Predictor/sudoku-solver/blob/master/src/com/predictor/sudoku/solver/SudokuBacktrackingSolver.java) – implements decision making conditions for backtracking search of the puzzle solution.
## Tests and test cases
  Unit tests for __SudokuBacktrackingSolver__ are located in [__SudokuSolverTests__](https://github.com/Predictor/sudoku-solver/blob/master/test/SudokuSolverTests.java) class.  
  Test cases can be found in [_sudoku-solver/test/resources_](https://github.com/Predictor/sudoku-solver/tree/master/test/resources) folder.  
  Required examples are marked as [__sample2__](https://github.com/Predictor/sudoku-solver/blob/master/test/resources/sample2.txt) and [__sample3__](https://github.com/Predictor/sudoku-solver/blob/master/test/resources/sample3.txt).  
  [__Sample4__](https://github.com/Predictor/sudoku-solver/blob/master/test/resources/sample4.txt) contains a Sudoku designed against brute force algorithms, such as backtracking. It takes approximately 1 minute to complete on [AMD-FX-8320](https://www.amd.com/ru/products/cpu/fx-8320).  
  Sample files with __“-bad”__ suffix contain unsolvable Sudoku examples.  
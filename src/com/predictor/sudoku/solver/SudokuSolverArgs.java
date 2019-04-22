package com.predictor.sudoku.solver;

public class SudokuSolverArgs {
    public static final String USAGE = "Usage: java -classpath <sudoku-solver_out_path> com.predictor.sudoku.solver.SudokuSolverApp <path_to_input_file>";
    public final String InputFilePath;
    public SudokuSolverArgs(String[] args){
        if(args == null || args.length < 1) throw new IllegalArgumentException("missing args");
        if(args.length > 1 || args[0] == null) throw new IllegalArgumentException("unexpected args");
        InputFilePath = args[0];
    }
}

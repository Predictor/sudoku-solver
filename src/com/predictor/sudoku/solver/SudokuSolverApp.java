package com.predictor.sudoku.solver;

public class SudokuSolverApp {

    public static void main(String[] args) {
        try {
            var sudokuSolverArgs = new SudokuSolverArgs(args);
            var boardProvider =  new FileSudokuBoardProvider(new NumberArrayFileReader(), sudokuSolverArgs.InputFilePath);
            var board = boardProvider.GetBoard();
            System.out.println("Sudoku board:");
            System.out.println(board.toString());
            var solver = new SudokuBacktrackingSolver(board);
            if(!solver.Solve()){
                System.out.println("The board is unsolvable.");
                return;
            }
            System.out.println();
            System.out.println("Solution:");
            var result = solver.GetResult();
            System.out.println(result.toString());
        }catch (Exception ex){
            System.out.println(String.format("%s%s%s", ex.getMessage(), System.lineSeparator(), SudokuSolverArgs.USAGE));
        }
    }
}

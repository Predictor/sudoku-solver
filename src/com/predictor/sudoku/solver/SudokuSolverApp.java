package com.predictor.sudoku.solver;

public class SudokuSolverApp {

    public static void main(String[] args) {
        try {
            var sudokuSolverArgs = new SudokuSolverArgs(args);
            var boardProvider =  new FileSudokuBoardProvider(new NumberArrayFileReader(), sudokuSolverArgs.InputFilePath);
            var solver = new SudokuBacktrackingSolver(boardProvider.GetBoard());
            if(!solver.Solve()){
                System.out.println("The board is unsolvable.");
                return;
            }
            var restult = solver.GetResult();
            for (int i = 0; i < SudokuBoard.BOARD_SIZE; i++) {
                System.out.println("");
                for (int j = 0; j < SudokuBoard.BOARD_SIZE; j++) {
                    System.out.print(String.format("%d", restult.GetCell(i,j)));
                }
            }
        }catch (Exception ex){
            System.out.println(String.format("%s\n%s", ex.getMessage(), SudokuSolverArgs.USAGE));
        }
    }
}

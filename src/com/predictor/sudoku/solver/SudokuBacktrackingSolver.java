package com.predictor.sudoku.solver;

public class SudokuBacktrackingSolver extends BacktrackingBase implements ISudokuSolver {

    private SudokuBoard sudokuBoard;
    private int row;
    private int column;

    public SudokuBacktrackingSolver(SudokuBoard sudokuBoard) {
        this.sudokuBoard = new SudokuBoard(sudokuBoard);
        row = 0;
        column = 0;
    }

    /**
     * @return true if solution is found, false otherwise
     */
    @Override
    public boolean Solve(){
        return this.TryFindSolution();
    }

    /**
     * @return solution if it was found, undefined result otherwise
     */
    @Override
    public SudokuBoard GetResult() {
        return new SudokuBoard(sudokuBoard);
    }

    @Override
    protected boolean TryMoveNext() {
        if (column < SudokuBoard.BOARD_SIZE - 1) {
            column++;
            return true;
        }
        if (row < SudokuBoard.BOARD_SIZE - 1) {
            row++;
            column = 0;
            return true;
        }

        return false;
    }

    @Override
    protected boolean TryExecuteStep() {
        if (sudokuBoard.IsPredefinedCell(row, column)) return true;

        for (byte i = (byte) (sudokuBoard.GetCell(row, column) + 1); i <= SudokuBoard.BOARD_SIZE; i++) {
            if (sudokuBoard.TrySetCell(row, column, i)) {
                return true;
            }
        }
        return false;
    }

    @Override
    protected boolean IsProblemSolved() {
        return row == SudokuBoard.BOARD_SIZE - 1
                && column == SudokuBoard.BOARD_SIZE - 1
                && sudokuBoard.GetCell(row, column) > 0;
    }

    @Override
    protected boolean TryMoveBack() {
        if (!sudokuBoard.IsPredefinedCell(row, column)){
            sudokuBoard.SetCell(row, column, (byte) 0);
        }
        while (true) {
            if (column > 0) {
                column--;
                if (sudokuBoard.IsPredefinedCell(row, column)) continue;
                return true;
            }
            if (row > 0) {
                row--;
                column = SudokuBoard.BOARD_SIZE - 1;
                if (sudokuBoard.IsPredefinedCell(row, column)) continue;
                return true;
            }
            return false;
        }
    }
}

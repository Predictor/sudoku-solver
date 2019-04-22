package com.predictor.sudoku.solver;

public class SudokuBoard {
    private static final byte boxSize = 3;
    public static final byte BOARD_SIZE = boxSize * boxSize;
    private static final byte clusterSize = (BOARD_SIZE * 3) - (boxSize * 2) - 1;
    public static final byte BOARD_ARRAY_SIZE = BOARD_SIZE * BOARD_SIZE;
    private byte[] board;
    private boolean[] isPredefinedCell;

    public SudokuBoard() {
        board = new byte[BOARD_ARRAY_SIZE];
        isPredefinedCell = new boolean[BOARD_ARRAY_SIZE];
    }

    public SudokuBoard(SudokuBoard sudokuBoard) {
        board = new byte[BOARD_ARRAY_SIZE];
        isPredefinedCell = new boolean[BOARD_ARRAY_SIZE];
        for (int i = 0; i < BOARD_ARRAY_SIZE; i++) {
            board[i] = sudokuBoard.board[i];
            isPredefinedCell[i] = sudokuBoard.isPredefinedCell[i];
        }
    }

    public SudokuBoard(byte[] boardArray) {
        if (boardArray == null || boardArray.length != BOARD_ARRAY_SIZE) {
            throw new IllegalArgumentException("boardArray");
        }
        board = new byte[BOARD_ARRAY_SIZE];
        isPredefinedCell = new boolean[BOARD_ARRAY_SIZE];
        for (int i = 0; i < BOARD_ARRAY_SIZE; i++) {
            board[i] = boardArray[i];
            isPredefinedCell[i] = board[i] != 0;
        }
    }

    /**
     * @param row    - row number starting from 0
     * @param column - column number starting from 0
     * @return cell value (0 means empty)
     */
    public byte GetCell(int row, int column) {
        validateIndex(row, column);
        return getCell(row, column);
    }

    /**
     * @param row    - row number starting from 0
     * @param column - column number starting from 0
     * @return true if cell was defined in initial board
     */
    public boolean IsPredefinedCell(int row, int column) {
        validateIndex(row, column);
        return isPredefinedCell[getCellIndex(row, column)];
    }

    /**
     * @param row    - row number starting from 0
     * @param column - column number starting from 0
     * @param value  - cell value (0 means empty)
     */
    public void SetCell(int row, int column, byte value) {
        validateIndex(row, column);
        validateValue(value);
        board[getCellIndex(row, column)] = value;
    }

    /**
     * @param row    - row number starting from 0
     * @param column - column number starting from 0
     * @param value  - cell value (0 means empty)
     * @return true if the value satisfies Sudoku rules, false otherwise
     */
    public boolean CanSetCell(int row, int column, byte value) {
        validateIndex(row, column);
        validateValue(value);
        return canSetCell(row, column, value);
    }

    /**
     * @param row    - row number starting from 0
     * @param column - column number starting from 0
     * @param value  - cell value (0 means empty)
     * @return true if the value satisfies Sudoku rules, false otherwise
     * @ Sets the value to a cell at [row][column] only when the value satisfies Sudoku rules
     */
    public boolean TrySetCell(int row, int column, byte value) {
        validateIndex(row, column);
        validateValue(value);
        if (canSetCell(row, column, value)) {
            board[getCellIndex(row, column)] = value;
            return true;
        }
        return false;
    }

    public String toString() {
        var sb = new StringBuilder();
        for (int i = 0; i < SudokuBoard.BOARD_SIZE; i++) {
            if (i > 0) {
                sb.append(System.lineSeparator());
            }
            for (int j = 0; j < SudokuBoard.BOARD_SIZE; j++) {
                sb.append(getCell(i, j));
            }
        }
        return sb.toString();
    }

    private boolean canSetCell(int row, int column, byte value) {
        if (value == 0) {
            return true;
        }
        if (value == getCell(row, column)) {
            return true;
        }

        for (int i : getClusterIndexes(row, column)) {
            if (board[i] == value) {
                return false;
            }
        }
        return true;
    }

    private int[] getClusterIndexes(int row, int column) {
        int[] indexes = new int[clusterSize];
        int k = 0;
        for (byte i = 0; i < BOARD_SIZE; i++) {
            if (row == i) continue;
            indexes[k++] = getCellIndex(i, column);
        }
        for (byte i = 0; i < BOARD_SIZE; i++) {
            if (column == i) continue;
            indexes[k++] = getCellIndex(row, i);
        }
        byte boxR = (byte) (row - (row % boxSize));
        byte boxC = (byte) (column - (column % boxSize));
        for (byte i = boxR; i < boxR + boxSize; i++) {
            if (row == i) continue;
            for (byte j = boxC; j < boxC + boxSize; j++) {
                if (column == j) continue;
                indexes[k++] = getCellIndex(i, j);
            }
        }
        return indexes;
    }

    private byte getCell(int row, int column) {
        return board[getCellIndex(row, column)];
    }

    private int getCellIndex(int row, int column) {
        return row * BOARD_SIZE + column;
    }

    private void validateValue(int value) {
        if (value < 0 || value > BOARD_SIZE) {
            throw new IllegalArgumentException(String.format("value must be >=0 and <=%d", BOARD_SIZE));
        }
    }

    private void validateIndex(int row, int column) {
        if (row < 0 || row >= BOARD_SIZE || column < 0 || column >= BOARD_SIZE) {
            throw new IllegalArgumentException(String.format("row/column number must be >=0 and <%d", BOARD_SIZE));
        }
    }
}

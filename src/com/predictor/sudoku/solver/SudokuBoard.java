package com.predictor.sudoku.solver;

public class SudokuBoard {
    private static final byte boxSize = 3;
    private static final byte boardSize = boxSize * boxSize;
    private static final byte clusterSize = (boardSize * 3) - (boxSize * 2) - 1;
    public static final byte BOARD_ARRAY_SIZE = boardSize * boardSize;
    private byte[] board;

    public SudokuBoard() {
        board = new byte[BOARD_ARRAY_SIZE];
    }

    public SudokuBoard(byte[] boardArray) {
        if (boardArray == null || boardArray.length != BOARD_ARRAY_SIZE) {
            throw new IllegalArgumentException("boardArray");
        }
        board = new byte[BOARD_ARRAY_SIZE];
        for (int i = 0; i < BOARD_ARRAY_SIZE; i++) {
            board[i] = boardArray[i];
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
     * @ Sets the value to a cell at [row][column] only when the value satisfies Sudoku rules
     * @param row    - row number starting from 0
     * @param column - column number starting from 0
     * @param value  - cell value (0 means empty)
     * @return true if the value satisfies Sudoku rules, false otherwise
     */
    public boolean TrySetCell(int row, int column, byte value) {
        validateIndex(row, column);
        validateValue(value);
        if(canSetCell(row, column, value)){
            board[getCellIndex(row, column)] = value;
            return true;
        }
        return false;
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
        for (byte i = 0; i < boardSize; i++) {
            if (row == i) continue;
            indexes[k++] = getCellIndex(i, column);
        }
        for (byte i = 0; i < boardSize; i++) {
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
        return row * boardSize + column;
    }

    private void validateValue(int value) {
        if (value < 0 || value > boardSize) {
            throw new IllegalArgumentException(String.format("value must be >=0 and <=%d", boardSize));
        }
    }

    private void validateIndex(int row, int column) {
        if (row < 0 || row >= boardSize || column < 0 || column >= boardSize) {
            throw new IllegalArgumentException(String.format("row/column number must be >=0 and <%d", boardSize));
        }
    }
}

package com.predictor.sudoku.solver;

public class ArraySudokuBoardProvider implements ISudokuBoardProvider {
    private byte[] array;

    public ArraySudokuBoardProvider(byte[] array){
        if(array == null || array.length != SudokuBoard.BOARD_ARRAY_SIZE){
            throw new IllegalArgumentException("array");
        }
        this.array = array;
    }

    @Override
    public SudokuBoard Get(){
        return new SudokuBoard(array);
    }
}

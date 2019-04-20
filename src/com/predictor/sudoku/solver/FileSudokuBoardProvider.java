package com.predictor.sudoku.solver;

public class FileSudokuBoardProvider implements ISudokuBoardProvider{
    private IArrayFileReader reader;
    private String path;

    public FileSudokuBoardProvider(IArrayFileReader reader, String path){
        this.reader = reader;
        this.path = path;
    }

    @Override
    public SudokuBoard Get() {
        var array = reader.Read(path, SudokuBoard.BOARD_ARRAY_SIZE);
        var arraySudokuBoardProvider = new ArraySudokuBoardProvider(array);
        return arraySudokuBoardProvider.Get();
    }
}

import com.predictor.sudoku.solver.FileSudokuBoardProvider;
import com.predictor.sudoku.solver.NumberArrayFileReader;
import com.predictor.sudoku.solver.SudokuBoard;
import org.junit.Assert;
import org.junit.Test;

import java.net.URL;
import java.util.Random;

public class SudokuBoardTests {
    private final static Random rnd = new Random();
    @Test
    public void EmptyBoardAllowsAnyValueInAnyCell() {
        var board = new SudokuBoard();
        for (byte row = 0; row < 9; row++) {
            for (byte col = 0; col < 9; col++) {
                for (byte value = 0; value <= 9; value++) {
                    Assert.assertTrue(String.format("CanSetCell(%d, %d, %d) expected to return true", row, col, value), board.CanSetCell(row, col, value));
                }
            }
        }
    }

    @Test
    public void ApplyValidSolution() {
        var reader = new NumberArrayFileReader();
        URL url = getClass().getResource("resources/sample1.txt");
        var board = new FileSudokuBoardProvider(reader, url.getFile()).Get();
        url = getClass().getResource("resources/sample1-solved.txt");
        var solvedBoard = new FileSudokuBoardProvider(reader, url.getFile()).Get();
        for (byte i = 0; i < 9; i++) {
            for (byte j = 0; j < 9; j++) {
                Assert.assertTrue(String.format("TrySetCell(%d, %d, %d) expected to return true", i, j, solvedBoard.GetCell(i, j)), board.TrySetCell(i, j, solvedBoard.GetCell(i, j)));
            }
        }
    }

    @Test
    public void TrySetIncorrectCellValue() {
        var reader = new NumberArrayFileReader();
        var url = getClass().getResource("resources/sample1-solved.txt");
        var solvedBoard = new FileSudokuBoardProvider(reader, url.getFile()).Get();
        for (byte i = 0; i < 9; i++) {
            for (byte j = 0; j < 9; j++) {
                var oldValue = solvedBoard.GetCell(i, j);
                Assert.assertNotEquals("Solved board must not contain 0 values",0, oldValue);
                byte newValue = oldValue;
                while(newValue == oldValue){
                    newValue = (byte) (rnd.nextInt(8) + 1);
                }
                Assert.assertFalse(String.format("TrySetCell(%d, %d, %d) expected to return false", i, j, newValue), solvedBoard.TrySetCell(i, j, newValue));
            }
        }
    }
}

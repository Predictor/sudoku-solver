import com.predictor.sudoku.solver.NumberArrayFileReader;
import com.predictor.sudoku.solver.SudokuBoard;
import org.junit.Assert;
import org.junit.Test;

public class NumberArrayFileReaderTests {

    @Test
    public void ReadsArray(){
        byte[] expected = {0, 0, 2, 0, 0, 0, 0, 4, 1, 0, 0, 0, 0, 8, 2, 0, 7, 0, 0, 0, 0, 0, 4, 0, 0, 0, 9, 2, 0, 0, 0, 7, 9, 3, 0, 0, 0, 1, 0, 0, 0, 0, 0, 8, 0, 0, 0, 6, 8, 1, 0, 0, 0, 4, 1, 0, 0, 0, 9, 0, 0, 0, 0, 0, 6, 0, 4, 3, 0, 0, 0, 0, 8, 5, 0, 0, 0, 0, 4, 0, 0};
        var fileName = getClass().getResource("resources/sample3.txt").getFile();
        var reader = new NumberArrayFileReader();
        var actual = reader.Read(fileName, SudokuBoard.BOARD_ARRAY_SIZE);
        Assert.assertArrayEquals(expected, actual);
    }
}

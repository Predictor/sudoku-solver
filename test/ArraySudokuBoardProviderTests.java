import com.predictor.sudoku.solver.ArraySudokuBoardProvider;
import com.predictor.sudoku.solver.SudokuBoard;
import org.junit.Assert;
import org.junit.Test;

public class ArraySudokuBoardProviderTests {

    @Test
    public void CreatesBoardFromArray() {
        byte[] array = {0, 0, 2, 0, 0, 0, 0, 4, 1, 0, 0, 0, 0, 8, 2, 0, 7, 0, 0, 0, 0, 0, 4, 0, 0, 0, 9, 2, 0, 0, 0, 7, 9, 3, 0, 0, 0, 1, 0, 0, 0, 0, 0, 8, 0, 0, 0, 6, 8, 1, 0, 0, 0, 4, 1, 0, 0, 0, 9, 0, 0, 0, 0, 0, 6, 0, 4, 3, 0, 0, 0, 0, 8, 5, 0, 0, 0, 0, 4, 0, 0};
        var provider = new ArraySudokuBoardProvider(array);
        var board = provider.GetBoard();
        for (int i = 0; i < SudokuBoard.BOARD_SIZE; i++) {
            for (int j = 0; j < SudokuBoard.BOARD_SIZE; j++) {
                Assert.assertEquals(array[i * SudokuBoard.BOARD_SIZE + j], board.GetCell(i, j));
            }
        }
    }
}
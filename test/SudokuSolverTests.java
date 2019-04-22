import com.predictor.sudoku.solver.FileSudokuBoardProvider;
import com.predictor.sudoku.solver.NumberArrayFileReader;
import com.predictor.sudoku.solver.SudokuBacktrackingSolver;
import org.junit.Assert;
import org.junit.Test;

public class SudokuSolverTests {
    @Test
    public void TestBackTracking_Sample1() {
        testBackTrackingWithSolvableSampleFile("resources/sample1.txt");
    }

    @Test
    public void TestBackTracking_Sample2() {
        testBackTrackingWithSolvableSampleFile("resources/sample2.txt");
    }

    @Test
    public void TestBackTracking_Sample3() {
        testBackTrackingWithSolvableSampleFile("resources/sample3.txt");
    }

    @Test
    public void TestBackTracking_Sample4() {
        testBackTrackingWithSolvableSampleFile("resources/sample4.txt");
    }

    @Test
    public void TestBackTracking_Sample5() {
        testBackTrackingWithSolvableSampleFile("resources/sample5.txt");
    }

    @Test
    public void TestBackTracking_Sample6() {
        testBackTrackingWithSolvableSampleFile("resources/sample6.txt");
    }

    @Test
    public void TestBackTracking_Sample7() {
        testBackTrackingWithSolvableSampleFile("resources/sample7.txt");
    }

    @Test
    public void TestBackTracking_BadSample1() {
        testBackTrackingWithBadSampleFile("resources/sample-bad1.txt");
    }

    @Test
    public void TestBackTracking_BadSample2() {
        testBackTrackingWithBadSampleFile("resources/sample-bad2.txt");
    }

    @Test
    public void TestBackTracking_BadSample3() {
        testBackTrackingWithBadSampleFile("resources/sample-bad3.txt");
    }

    @Test
    public void TestBackTracking_BadSample4() {
        testBackTrackingWithBadSampleFile("resources/sample-bad4.txt");
    }

    private void testBackTrackingWithSolvableSampleFile(String fileName){
        var url = getClass().getResource(fileName);
        var reader = new NumberArrayFileReader();
        var provider = new FileSudokuBoardProvider(reader, url.getFile());
        var boardToSolve = provider.GetBoard();
        var solver = new SudokuBacktrackingSolver(boardToSolve);
        Assert.assertTrue("Failed to find solution.", solver.Solve());
        var solvedBoard = solver.GetResult();
        for (byte i = 0; i < 9; i++) {
            for (byte j = 0; j < 9; j++) {
                Assert.assertNotEquals(String.format("Expected solvedBoard[%d][%d] != 0", i, j), 0, solvedBoard.GetCell(i, j));
                Assert.assertTrue(String.format("TrySetCell(%d, %d, %d) expected to return true", i, j, solvedBoard.GetCell(i, j)), boardToSolve.TrySetCell(i, j, solvedBoard.GetCell(i, j)));
            }
        }
    }

    private void testBackTrackingWithBadSampleFile(String fileName){
        var url = getClass().getResource(fileName);
        var reader = new NumberArrayFileReader();
        var provider = new FileSudokuBoardProvider(reader, url.getFile());
        var boardToSolve = provider.GetBoard();
        var solver = new SudokuBacktrackingSolver(boardToSolve);
        Assert.assertFalse("Must have failed.", solver.Solve());
    }

}

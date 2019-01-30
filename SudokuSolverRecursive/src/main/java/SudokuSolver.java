import java.io.IOException;

public class SudokuSolver {
    private Board board;

    private SudokuSolver(String filePath) throws IOException {
        this.board = new Board(filePath);
    }

    public static void main(String [] args) throws IOException {
        SudokuSolver solver = new SudokuSolver("src/test/resources/puzzle.ss");
        System.out.println(solver.board);
    }
    
    public Board solve() {
    	
    }
    
    public Board solveHelper () {
    	
    }
}

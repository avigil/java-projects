import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class SudokuSolver {
    private Board board;

    private SudokuSolver(String filePath) throws IOException {
        this.board = new Board(filePath);
    }

    public static void main(String [] args) throws IOException {
        SudokuSolver solver = new SudokuSolver("src/test/resources/puzzle.ss");
        System.out.println(solver.board);
        
        SudokuSolver solver2 = new SudokuSolver("src/test/resources/puzzleSolvedCompletePuzzle_3_missing.ss");
        System.out.println(solver2.board);
        
        System.out.println("solved 2 board:\n" + solver2.solve());
    }
    
    public Board solve() {
    	Board solvedBoard = solveHelper(board);
    	return solvedBoard;
    }
    
    public Board solveHelper (Board boardToSend) {
    	//base case
    	if(boardToSend.isSolved()) {
    		return boardToSend;
    	}
    	
    	Board returnBoard=null;
    	
    	//otherwise we need to check all of the neighbors
    	List<Board> neighbors = boardToSend.getNeighbors();
    	/*
    	Optional<Board> solvedNeighbor = neighbors.parallelStream().filter(b -> b.isSolved()).findAny();
    	if(solvedNeighbor.isPresent()) {
    		return solvedNeighbor.get();
    	}
    	*/
    	Optional<Board> solvedRecursiveBoard = neighbors.parallelStream().map(board -> {
    			return solveHelper(board);	
    	}).filter(b -> b!=null && b.isSolved()).findAny();
    	//filter(b -> solveHelper(b)!=null && solveHelper(b).isSolved())
    	
    	if(solvedRecursiveBoard.isPresent()) {
    		return solvedRecursiveBoard.get();
    	}
    	
    	/*
    	for(Board b: neighbors) {
    		if(b.isSolved()) {
    			return b;
    		} else if(b.isValid()){
    			Board recursiveBoard = solveHelper(b);
    			if(recursiveBoard!=null && recursiveBoard.isSolved()) {
    				return recursiveBoard;
    			}
    			//System.out.println(recursiveBoard);
    		}
    		/*
    		try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
    	}*/
    	return returnBoard;
    }
}

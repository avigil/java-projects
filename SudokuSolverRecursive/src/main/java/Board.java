import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Board {
    private final List<List<Integer>> board;

    Board(String filePath) throws IOException {
    	BoardReader br = BoardReaderFactory.getBoardReader(filePath);
    	board = br.readBoard(filePath);
    	
    }

    List<List<Integer>> getBoard() {
        return board;
    }
    
   
    public String toString() {
        StringBuilder boardString = new StringBuilder();
        for (List<Integer> row : this.board) {
            StringBuilder rowString = new StringBuilder();
            for (Integer item : row) {
                if (item == null) {
                    rowString.append('.');
                } else {
                    rowString.append(item.toString());
                }
            }

            boardString.append(rowString);
            boardString.append("\n");
        }

        return boardString.toString();
    }
    
    boolean isValid(){
    	return numbersValid() && rowCheck() && columnCheck() && gridCheck();
    }
    
    private boolean numbersValid() {
    	for (int row = 0; row < board.size(); row++) {
    		List<Integer> rowList = board.get(row);
    		for (int column = 0; column < rowList.size(); column++) {
    			if (rowList.get(column) != null) {
    				if (rowList.get(column) < 1 || rowList.get(column) > 9) {
    					return false;
    				}
    			}
    		}
    	}
    	return true;
    }
    
    private boolean rowCheck() {
    	for (int row = 0; row < board.size(); row++) {
    		List<Integer> rowList = board.get(row);
    		Set<Integer> rowSet = new HashSet<>();
    		for (int column = 0; column < rowList.size(); column++) {
    			Integer currentValue = rowList.get(column);
    			if (currentValue != null) {
    				if (rowSet.contains(currentValue)) {
    					return false;
    				} else {
    					rowSet.add(currentValue);
    				}
    			} 
    		}

    	}
		return true;
    }
    
    private boolean columnCheck() {
    	for (int col = 0; col < board.size(); col++) {
    		Set<Integer> colSet = new HashSet<>();
    		for (int row = 0; row < board.get(0).size(); row++) {
    			Integer currentValue = board.get(row).get(col);
    			if (currentValue != null) {
    				if (colSet.contains(currentValue)) {
    					return false;
    				} else {
    					colSet.add(currentValue);
    				}
    			} 
    		}

    	}
		return true;
    }
    
    private boolean gridCheck() {
    	for (int rowOffset = 0; rowOffset < board.size(); rowOffset+=3) {
    		for (int colOffset = 0; colOffset < board.size(); colOffset+=3) {
    		Set<Integer> squareSet = new HashSet<>();
    		for (int row = 0; row < 3; row++) {
    			for (int col = 0; col < 3; col++) {
    				Integer currentValue = board.get(rowOffset + row).get(colOffset + col);
    				if (currentValue != null) {
    					if (squareSet.contains(currentValue)) {
    						return false;
    					} else {
    						squareSet.add(currentValue);
    					}
    				}
    		
    			}
    		}

    		}

    	}
	return true;
    }
    
    public ArrayList<Board> getNeighbors() {
    	
    }
    
    
    boolean isSolved () {
    	boolean isValid = isValid();
    	if (! isValid) {
    		return false;
    	} else {
    		for (int row = 0; row < board.size(); row++) {
    			List<Integer> rowList = board.get(row);
    			for (int column = 0; column < rowList.size(); column++) {
    				Integer currentValue = rowList.get(column);
    				if (currentValue == null) {
    					return false;
    				} 
    			} 
    		}
    		return true;
    	}
    	    	
    }
    
}

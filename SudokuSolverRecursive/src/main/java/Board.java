import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

class Board {
    private final List<List<Integer>> board;

    Board(String filePath) throws IOException {
    	BoardReader br = BoardReaderFactory.getBoardReader(filePath);
    	board = br.readBoard(filePath);
    	
    }
    
    Board(List<List<Integer>> boardList) {
    	board = boardList;
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
    	boolean nValid = numbersValid();
    	boolean rValid = rowCheck();
    	boolean cValid = columnCheck();
    	boolean gValid = gridCheck();
    	
    	return nValid && rValid && cValid && gValid;
    	
    	//return numbersValid() && rowCheck() && columnCheck() && gridCheck();
    }
    
    private boolean numbersValid() {
    	return board.parallelStream().flatMap(List::stream).allMatch(x -> x==null || (x > 0 && x < 10) );
    	/*
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
    	*/
    }
    
    private boolean rowCheck() {
    	return !(board.parallelStream().map(row -> {
    		Set<Integer> duplicatedNumbersRemovedSet = new HashSet<>();
    		Optional<Integer> duplicate = row.parallelStream().filter(n->n!=null).filter(n -> !duplicatedNumbersRemovedSet.add(n)).findAny();
    		return duplicate.isPresent();
    		//row.parallelStream().filter(i -> Collections.frequency(row, i) >1).collect(Collectors.toSet()) )
    	}).filter(b -> b).findAny().isPresent()
    			);
    	
    	//flatMap(List::stream).allMatch(x -> x==null || (x > 0 && x < 10) );
    	
    	
    	/*
    	for (int row = 0; row < board.size(); row++) {
    		List<Integer> rowList = board.get(row);
    		//Set<Integer> rowSet = new HashSet<>();
    		boolean [] rowArray = new boolean[9];
    		for (int column = 0; column < rowList.size(); column++) {
    			Integer currentValue = rowList.get(column);
    			if (currentValue != null) {
    				if (rowArray[currentValue-1]) {
    					return false;
    				} else {
    					rowArray[currentValue-1]=true;
    				}
    			} 
    			
    			/*if (currentValue != null) {
    				if (rowSet.contains(currentValue)) {
    					return false;
    				} else {
    					rowSet.add(currentValue);
    				}
    			} */
    //		}

    //	}
	//	return true;
    }
    
    private boolean columnCheck() {
    	for (int col = 0; col < board.size(); col++) {
    		//Set<Integer> colSet = new HashSet<>();
    		boolean [] colArray = new boolean[9];
    		for (int row = 0; row < board.get(0).size(); row++) {
    			Integer currentValue = board.get(row).get(col);
    			//int currentValueInt = board.get(row).get(col);
    			if (currentValue != null) {
    				/*if (colSet.contains(currentValue)) {
    					return false;
    				} else {
    					colSet.add(currentValue);
    				}*/
    				
    				if (colArray[currentValue-1]) {
    					return false;
    				} else {
    					colArray[currentValue-1]=true;
    				}
    			} 
    		}

    	}
		return true;
		
    }
    
    private boolean gridCheck() {
    	for (int rowOffset = 0; rowOffset < board.size(); rowOffset+=3) {
    		for (int colOffset = 0; colOffset < board.size(); colOffset+=3) {
    		//Set<Integer> squareSet = new HashSet<>();
    		boolean [] squareArray = new boolean[9];
    		for (int row = 0; row < 3; row++) {
    			for (int col = 0; col < 3; col++) {
    				Integer currentValue = board.get(rowOffset + row).get(colOffset + col);
    				if (currentValue != null) {
    					if (squareArray[currentValue-1]) {
    						return false;
    					} else {
    						squareArray[currentValue-1]=true;
    					}
    					
    					
    					/*if (squareSet.contains(currentValue)) {
    						return false;
    					} else {
    						squareSet.add(currentValue);
    					}*/
    				}
    		
    			}
    		}
    		squareArray = null;

    		}

    	}
	return true;
    }
    
    public ArrayList<Board> getNeighbors() {
    	
    	//board.parallelStream().flatMap(List::stream).allMatch(x -> x==null || (x > 0 && x < 10) );
    	
    	
    	
    	
    	ArrayList<Board> neighbors = new ArrayList<Board>();
    	//go through the entire board and for each null spot, try every possible number
    	for(int row=0;row<board.size();row++) {
    		for(int column =0;column<board.get(row).size();column++) {
    			List<Integer> rowValues = board.get(row);
    			if(rowValues.get(column) ==null) {
	    			for(int value = 1; value<10;value++) {
	    				List<List<Integer>> duplicate = duplicateBoard(board);
	    				List<Integer> changingRowValues = duplicate.get(row);
	    				changingRowValues.set(column, value);
	    				duplicate.set(row, changingRowValues);
	    				//need to check if the created board is valid
	    				Board boardToCheck = new Board(duplicate);
	    				if(boardToCheck.isValid()) {
	    					neighbors.add(boardToCheck);
	    				}
    				}
    			}
    		
    		}
    	}
    	return neighbors;
    }
    
    private List<List<Integer>> duplicateBoard(List<List<Integer>> boardToDuplicate){
    	List<List<Integer>> copy = new ArrayList<List<Integer>>();
    	for(int r=0;r<boardToDuplicate.size();r++) {
    		List<Integer> row = new ArrayList<Integer>();
    		for(int c=0;c<boardToDuplicate.get(0).size();c++) {
    			row.add(boardToDuplicate.get(r).get(c));
    		}
    		copy.add(row);
    	}
    	return copy;
    }

    
    
    boolean isSolved () {
    	boolean isValid = isValid();
    	if (! isValid) {
    		return false;
    	} else {
    		boolean anyNulls = board.parallelStream().flatMap(List::stream).anyMatch(x -> x==null);
    		return !anyNulls;
    		/*
    		for (int row = 0; row < board.size(); row++) {
    			List<Integer> rowList = board.get(row);
    			for (int column = 0; column < rowList.size(); column++) {
    				Integer currentValue = rowList.get(column);
    				if (currentValue == null) {
    					return false;
    				} 
    			} 
    		}
    		return true;*/
    	}
    	    	
    }
    
    /*
    public static Board clone(Board original) {
    	original.getBoard().parallelStream().
    }
    */
}

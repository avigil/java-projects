import java.io.IOException;
import java.util.List;

public interface BoardReader {
	List<List<Integer>> readBoard(String filename) throws IOException;
} 



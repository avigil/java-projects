import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class SDKBoardReader implements BoardReader {

	@Override
	public List<List<Integer>> readBoard(String filename) throws IOException {
		Path path = Paths.get(filename);

        List<List<Integer>> list = Files.lines(path).map(s -> s.chars().mapToObj(value -> {
            if (value == '.') {
                return null;
            } else {
                return Character.getNumericValue(value);
            }
        }).collect(Collectors.toList())).collect(Collectors.toList());
        
        return list;
	}

	

}

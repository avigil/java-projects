import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class SSBoardReader implements BoardReader {

	@Override
	public List<List<Integer>> readBoard(String filename) throws IOException {
		Path path = Paths.get(filename);
		
		//Integer i = Integer.valueOf(-1);
		//i. .filter(i -> i.intValue()!=-1)
        

    List<List<Integer>> list = Files.lines(path).filter(s -> !s.matches("[-]+")).map(s -> s.chars().filter(c->c!='|').mapToObj(value -> {
        if (value == '.') {
            return null;
        } else {
            return Character.getNumericValue(value);
        }
    }).collect(Collectors.toList())).collect(Collectors.toList());

        return list;
}
	

}

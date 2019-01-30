
public class BoardReaderFactory {
	static BoardReader getBoardReader(String filename) {
		BoardReader br = null;
		if(filename.endsWith(".ss")) {
			br = new SSBoardReader();	
		} else if (filename.endsWith(".sdk")) {
			br = new SDKBoardReader();
		}
		return br;
	}
	
}

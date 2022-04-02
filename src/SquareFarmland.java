import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.StringTokenizer;

public class SquareFarmland {

	public static void main(String[] args) {
		List<String> lines;
		try {
			lines = Files.readAllLines(Paths.get("test data/largestsquare.txt"));
			int rows = Integer.parseInt(lines.remove(0));
			int[][] matrix = new int[rows][rows];
			for(int i = 0; i < rows; i++) {
				String line = lines.get(i);
				StringTokenizer tokenizer = new StringTokenizer(line);
				int j = 0;
				while(tokenizer.hasMoreElements()) {
					String token = tokenizer.nextToken();
					matrix[i][j] = Integer.parseInt(token);
					j++;
				}
			}

			int foundOcc = 0;
			String foundLoc = "";

			for(int i = 0; i < rows; i++) {
				for(int j = 0; j < rows; j++) {
					int ele = matrix[i][j];
					if(ele == 1) {
						int cRowX = 1;
						for(int k = j; k < rows; k++) {
							int eleTwo = matrix[i][k];
							if(eleTwo == 1) {
								cRowX++;
							} else {
								break;
							}
						}

						int cRowY = 1;
						for(int k = i; k < rows; k++) {
							int eleTwo = matrix[k][j];
							if(eleTwo == 1) {
								cRowY++;
							} else {
								break;
							}
						}

						int minOcc = Math.min(cRowX, cRowY) - 1;
						if(minOcc > foundOcc) {						
							boolean valid = false;

							for(int mXT = foundOcc + 1; mXT <= minOcc; mXT++) {
								boolean v = false;
								l : for(int m = i; m < i + mXT - 1; m++) {
									for(int q = j; q < j + mXT - 1; q++) {
										int eleFound = matrix[m][q];
										if(eleFound == 0) {
											v = true;
											break l;
										}
									}
								}
								
								if(!v) {
									minOcc = mXT;
									break;
								}
								
							}

							if(valid) {
								foundLoc = i + ", " + j;
								foundOcc = minOcc;
							}
						}
					}
				}
			}
			
			if(!foundLoc.isEmpty()) {
				System.out.println("Biggest plot is at " + foundLoc + " of size " + Integer.valueOf((int) Math.pow(foundOcc, 2)) + " hectare");
			}

		} catch (IOException e) {
		}

	}

}

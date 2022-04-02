import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class DonutTrial {

	public static void main(String[] args) {
		try {
			List<String> lines = Files.readAllLines(Paths.get("test data/donut.txt"));
			String rowsColumns = lines.remove(0);
			int rows = Integer.parseInt(rowsColumns.split(" ")[0]);
			int columns = Integer.parseInt(rowsColumns.split(" ")[1]);
			String startPoint = lines.remove(0);
			int startRow = Integer.parseInt(startPoint.split(" ")[0]);
			int startColumn = Integer.parseInt(startPoint.split(" ")[1]);
			
			int[][] matrix = new int[rows][columns];
			for(int i = 0; i < rows; i++) {
				String line = lines.remove(0);
				StringTokenizer tokenizer = new StringTokenizer(line);
				for(int j = 0; j < columns; j++) {
					matrix[i][j] = Integer.parseInt(tokenizer.nextToken());
				}
			}

			int currentX = startColumn;
			int currentY = startRow;
			int totalDonuts = matrix[currentY][currentX];
			List<String> crossed = new ArrayList<>();
			crossed.add(currentY + ", " + currentX);
			
			while(true) {
				int up = -1, down = -1, left = -1, right = -1;
				
				if(currentY - 1 >= 0) 
				    up = matrix[currentY - 1][currentX];
				
				if(currentY + 1 < rows) 
				    down = matrix[currentY + 1][currentX];
				
				if(currentX - 1 >= 0) 
				    left = matrix[currentY][currentX - 1];
				
				if(currentX + 1 < columns) 
				    right = matrix[currentY][currentX + 1];

				String upStr = (currentY - 1) + ", " + currentX;
			    String downStr = (currentY + 1) + ", " + currentX;
				String leftStr = (currentY) + ", " + (currentX - 1);
				String rightStr = (currentY) + ", " + (currentX + 1);
				
				if(crossed.contains(upStr)) up = -1;
				if(crossed.contains(downStr)) down = -1;
				if(crossed.contains(leftStr)) left = -1;
				if(crossed.contains(rightStr)) right = -1;
				
				if(up == -1 && down == -1 && left == -1 && right == -1)
					break;
				
				if(up >= down && up >= left && up >= right)
					currentY -= 1;
				else if (down >= up && down >= left && down >= right)
					currentY += 1;
				else if (right >= up && right >= down && right >= left)
					currentX += 1;
				else if (left >= up && left >= down && left >= right)
					currentX -= 1;

				crossed.add(currentY + ", " + currentX);
				totalDonuts += matrix[currentY][currentX];
			}
			
			System.out.println(totalDonuts + " donuts were found");
		} catch (IOException e) {
		}
		
	}

}

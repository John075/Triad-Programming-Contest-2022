import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.StringTokenizer;

public class CorrectChange {

	public static void main(String[] args) {
		List<String> lines;
		try {
			lines = Files.readAllLines(Paths.get("test data/money.txt"));
			
			int amount = Integer.parseInt(lines.remove(0));
			for(int n = 0; n < amount; n++) {
				int numOfTransactions = Integer.parseInt(lines.remove(0));
				int numOfFives = 0, numOfTens = 0, numOfTwentys = 0;
				StringTokenizer tokenizer = new StringTokenizer(lines.remove(0));
				
				boolean incorrect = false;
				for(int j = 0; j < numOfTransactions; j++) {
					int bill = Integer.parseInt(tokenizer.nextToken());
					if(bill == 5) {
						numOfFives++;
					} else if (bill == 10) { //give back 5
						if(numOfFives >= 1) {
							numOfFives--;
							numOfTens++;
						} else {
							incorrect = true;
						}
					} else if (bill == 20) { //give back 15
					    if (numOfTens >= 1 && numOfFives >= 1) {
					    	numOfTens--;
					    	numOfFives--;
					    	numOfTwentys++;
					    } else if(numOfFives >= 3) {
					    	numOfFives -= 3;
					    	numOfTwentys++;
						} else {
							incorrect = true;
						}
					}
				}
				
				System.out.println(!incorrect);
			}
		} catch (IOException e) {
		}

	}

}

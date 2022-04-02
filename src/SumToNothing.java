import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class SumToNothing {

	public static void main(String[] args) {
		try {
			List<String> list = Files.readAllLines(Paths.get("test data/zero.txt"));
			int nu = Integer.parseInt(list.remove(0));
			for(int l = 0; l < nu; l++) {
				int numbers = Integer.parseInt(list.remove(0));
				String numberLine = list.remove(0);
				StringTokenizer tokenizer = new StringTokenizer(numberLine);
				
				int[] arr = new int[numbers];
				int x = 0;
				while(tokenizer.hasMoreTokens()) {
					String tok = tokenizer.nextToken();
					arr[x] = Integer.parseInt(tok);
					x++;
				}
				
				List<Integer> foundNums = null;
		        for (int i = 0; i < (1<<arr.length); i++) {
		            List<Integer> nums = new ArrayList<Integer>();
		            int cV = 0;
		            for (int j = 0; j < arr.length; j++) {
		                if ((i & (1 << j)) > 0) {
		                    cV += arr[j];
		                    nums.add(arr[j]);
		                }
		            }
		            
		            if(nums.size() > 0 && cV == 0) {
		            	foundNums = nums;
		            	break;
		            }
		        }
		        
		        if(foundNums != null) {
		        	String output = "";
		        	for(int i = 0; i < foundNums.size(); i++) {
		        		if(i != foundNums.size() - 1) {
		        			output += foundNums.get(i) + ", ";
		        		} else {
		        			output += foundNums.get(i) + "";
		        		}
		        	}
		        	
		        	System.out.println(output);
		        } else {
		        	System.out.println("No zero sum");
		        }
			}
		} catch (IOException e) {
		}
	}
}

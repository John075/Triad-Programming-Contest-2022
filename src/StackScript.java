import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StackScript {

	public static void main(String[] args) {
		try {
			List<String> list = Files.readAllLines(Paths.get("test data/script.txt"));
			
			Map<String, Integer> valueStore = new HashMap<>();
			
			List<Integer> stack = new ArrayList<>();
			
			for(String command : list) {
				if(command.equals("display")) {
					System.out.println("Top of stack: " + stack.get(0));
				} else if (command.startsWith("push")) {
					String var = command.split("\\s+")[1];
					stack.add(0, valueStore.get(var));
				} else if (command.startsWith("pop")) {
					String var = command.split("\\s+")[1];
					valueStore.put(var, stack.remove(0));
				} else if (command.equals("add")) {
					stack.add(0, stack.remove(0) + stack.remove(0));
				} else if (command.equals("sub")) {
					stack.add(0, stack.remove(0) - stack.remove(0));
				} else if (command.equals("mult")) {
					stack.add(0, stack.remove(0) * stack.remove(0));
				} else if (command.equals("div")) {
					stack.add(0, stack.remove(0) / stack.remove(0));
				} else if (command.equals("end") ) {
			
 				} else {
					if(command.split("\\s+").length == 2) {
						String name = command.split("\\s+")[0];
						int value = Integer.parseInt(command.split("\\s+")[1]);
						
						valueStore.put(name, value);
					}
				}
			}
		} catch (IOException e) {
		}
		
	}

}

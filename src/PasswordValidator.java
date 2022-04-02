import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class PasswordValidator {

	public static void main(String[] args) {
		try {
			List<String> passwords = Files.readAllLines(Paths.get("test data/password.txt"));
			passwords.remove(0); 
			for(String password : passwords) {
				try {
					boolean val1 = password.length() >= 8 && password.length() <= 32;
					boolean val2 = checkForOthers(password);
					boolean val3 = !Character.isDigit(password.charAt(0));

					if(val1 && val2 && val3) {
						System.out.println(password + " YES");
					} else {
						System.out.println(password + " NO");
					}
				} catch (Exception ex) {
					System.out.println(password + " NO");
				}
			}
		} catch (Exception e) {
		}

	}

	private static boolean checkForOthers(String password) {
		boolean hasDigit = false, hasUpperCase = false, hasLowerCase = false, hasInvalidChar = false;
		for(char c : password.toCharArray()) {
			if(Character.isDigit(c)) {
				hasDigit = true;
			}
			
			if(Character.isUpperCase(c)) {
				hasUpperCase = true;
			}
			
			if(Character.isLowerCase(c)) {
				hasLowerCase = true;
			}
			
			if(c == ' ' || c == '/') {
				hasInvalidChar = true;
			}
		}

		return !hasInvalidChar && hasLowerCase && hasUpperCase && hasDigit;
	}

}

package in.mini2;

import java.security.SecureRandom;

public class PasswordGeneratorUtils {

	private static final String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String CHAR_UPPER = CHAR_LOWER.toUpperCase();
    private static final String NUMBER = "0123456789";
    private static final String OTHER_CHAR = "!@#$%&*()_+-=[]{}|;':,.<>?";

    private static final String PASSWORD = CHAR_LOWER + CHAR_UPPER + NUMBER + OTHER_CHAR;

    private static final SecureRandom random = new SecureRandom();

    public static String generateRandomPassword() {
        
        StringBuilder password = new StringBuilder();

    
        password.append(getRandomChar(CHAR_LOWER));
        password.append(getRandomChar(CHAR_UPPER));
        password.append(getRandomChar(NUMBER));
        password.append(getRandomChar(OTHER_CHAR));

  
        for (int i = 4; i <= 8; i++) {
            password.append(getRandomChar(PASSWORD));
        }

        return password.toString();
    }

    private static char getRandomChar(String characterSet) {
        int randomIndex = random.nextInt(characterSet.length());
        return characterSet.charAt(randomIndex);
    }
	
	
}

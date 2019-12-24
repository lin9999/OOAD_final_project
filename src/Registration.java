import java.util.Random;

public class Registration {
	/**
	 * This method produce randomly return a String with assigned length.
	 * 
	 * @param length the assigned length.
	 * @return String the random String.
	 */
	public static String getRandomString(int length) {
		String str = "abcdefghigklmnopkrstuvwxyzABCDEFGHIGKLMNOPQRSTUVWXYZ0123456789";
		Random random = new Random();
		StringBuffer sf = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(62);// 0~61
			sf.append(str.charAt(number));
		}
		return sf.toString();
	}
	/**
	 * This method checks whether the User can be signed in.
	 * 
	 * @param UserID the current user's ID
	 * @param Password the current user's password
	 * @return int 1 if the user can be signed, 0 if user's ID is unknown, -1 if the password is wrong
	 */
	public static int SignInCheck(String UserID, String Password) { 
		User user = databaseUtil.getUser(UserID);
		if (user == null) return 0;
		else if (!Password.equals(user.getPassword())) return -1;
		return 1;
	}
	/**
	 * This method checks whether the current user's ID doesn't exist.
	 * 
	 * @param UserID the current user's ID
	 * @return boolean true if user's ID doesn't exist.
	 */
	public static boolean SignUpCheck(String UserID) {
		return databaseUtil.getUser(UserID) == null;
	}

}

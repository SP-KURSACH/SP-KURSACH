package userdata;

import java.util.HashMap;

public class Passwords {
	private static HashMap<String, String> passwords;
	
	public static void load(String file) {
		passwords = new HashMap<>();
		passwords.put("dan", "123"); // TODO: implement this.
	}
	
	public static String get(String login) {
		return passwords.get(login);
	}
}

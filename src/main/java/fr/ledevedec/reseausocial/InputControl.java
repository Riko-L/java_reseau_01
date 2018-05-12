package fr.ledevedec.reseausocial;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputControl {

	
	public static boolean lastNameControl(String lastname) {
		Pattern p = Pattern.compile("^[A-Za-z]{1,25}$");
		Matcher m = p.matcher(lastname);
		boolean b = m.matches();
		return b;
	}
	
	public static boolean firstNameControl(String firstname) {
		Pattern p = Pattern.compile("^[A-Za-z]{1,25}$");
		Matcher m = p.matcher(firstname);
		boolean b = m.matches();
		return b;
	}
	
	public static boolean pseudoControl(String pseudo) {
		Pattern p = Pattern.compile("^[A-Za-z0-9]{4,15}$");
		Matcher m = p.matcher(pseudo);
		boolean b = m.matches();
		return b;
	}
	
	public static boolean dateControl(String date) {
		Pattern p = Pattern.compile("^[0-9]{4}$");
		Matcher m = p.matcher(date);
		boolean b = m.matches();
		return b;
	}
	

}

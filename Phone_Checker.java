package format_exceptions;
import java.util.regex.*;//regex library is required for code
import java.util.*;//For scanner

public class Phone_Checker {
	public static void main(String[] args) {
		String phone, pat1="^(?:\\+971|00971|0)?(?:50|51|52|55|56|2|3|4|6|7|9)\\d{7}$";//Don't completely know how this regex string works but I can get it modified
		Scanner c = new Scanner(System.in);
		do {
		System.out.println("Enter the Phone Number: ");
		phone=c.nextLine();
		  Pattern pattern = Pattern.compile(pat1);//compiling the pattern, saves time in future
	       Matcher matcher = pattern.matcher(phone);//Which string is to be checked
		if(matcher.matches())//Actual comparison happens here
			System.out.println("Correct");
		else
			System.out.println("Wrong");
		}while(!phone.equals("br"));//Write br to exit the loop
		c.close();
	}
}

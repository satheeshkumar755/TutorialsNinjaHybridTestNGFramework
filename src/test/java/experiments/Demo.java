package experiments;

import java.util.Date;

public class Demo {
	
	public static void main(String[] args) {
		Date date = new Date();
		String checkDate = date.toString().replace(" ", "").replace(":", "");
		System.out.println(checkDate);
	}

}

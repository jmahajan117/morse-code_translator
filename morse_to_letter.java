import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class morse_to_letter {
	private String input;
	public morse_to_letter(String i)
	{
		input = i;
	}
	public String convert() throws FileNotFoundException
	{//input is the string inside
		String[] dictionary = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
				"K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W",
				"X", "Y", "Z", "1", "2", "3", "4", "5", "6", "7", "8", "9", "0"};
		File file = new File("Last.txt");
		String result = "";
		String s1 = "";
		int n = 0;
		for (int i = 0; i < input.length(); i++)
		{
			Scanner sc = new Scanner(file);
			if ((Character.isWhitespace(input.charAt(i)) || input.charAt(i) == '/') || 
					input.length() <= 5)
			{
				if (input.length() <= 5)
				{
					s1 = input.substring(0, i) + input.substring(i);
				}
				else
					s1 = input.substring(0, i) + input.substring(i + 1, i + 1);
				while (sc.hasNextLine())
				{
					if (sc.nextLine().equals(s1))
					{
						if (input.charAt(i) == '/')
							result += dictionary[n] + " ";
						else
							result += dictionary[n];
						break;
					}
					n++;
				}
				n = 0;
				input = input.substring(i + 1, input.length());
				i = 0;
			}
			sc.close();
		}
		return result;
	}
}

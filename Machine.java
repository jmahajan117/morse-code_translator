import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
public class Machine{
	public static void main(String[] args) throws HeadlessException, FileNotFoundException 
	{
		int cont = JOptionPane.YES_OPTION;
		JOptionPane.showMessageDialog(null,"<HTML><font face ='Arial' size='10' color='blue'>"
				+ "Welcome to SH_Machine");
		do{
		String input;
		int i;
		Object[] options = {"Text to Audio", "Morse to Audio"};
		i = JOptionPane.showOptionDialog(null, "Choose an option, ", "Choose an Option", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE,null, options, options[0]);
		if (i == 0)
		{ 
			input = JOptionPane.showInputDialog("Enter a message to be read");
			TTS sentence = new TTS();
			sentence.say(input);
		}
		if (i == 1)
		{
			morse_to_letter n = new morse_to_letter(MorseCodeRecorder.getMorseCodeInput(200));
			//JOptionPane.showMessageDialog(null, "The message was " + n.convert());
			TTS sentence = new TTS();
			String s = n.convert();
			sentence.say(s);
			JOptionPane.showMessageDialog(null, "The Message was " + s);
		}
		Object[] choose = {"Yes", "NO"};
		cont = JOptionPane.showOptionDialog(null, "Would you like to try again", "HACKNA", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE,null,choose , choose[0]);
		}while (cont == JOptionPane.YES_OPTION);
	}
}

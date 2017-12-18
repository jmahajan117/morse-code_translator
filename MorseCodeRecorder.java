import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.net.SocketTimeoutException;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MorseCodeRecorder extends JPanel {
	private static boolean spacebarCurrentlyDepressed=false;
	private static Stopwatch sw;
	
	public MorseCodeRecorder() {
		KeyListener listener = new MyKeyListener();
		addKeyListener(listener);
		setFocusable(true);
	}
	
	public static String getMorseCodeInput(long unitLength) {
		String resultString="";
		char queuedUpCharacter='\0';
		sw=new Stopwatch();
		
		JFrame frame = new JFrame("Recording Morse Code Input...");
		MorseCodeRecorder keyboardExample = new MorseCodeRecorder();
		frame.add(keyboardExample);
		frame.setSize(200, 200);
		frame.setVisible(true);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		while( ! ( sw.getTime()>5000 && sw.hasBeenStarted()  ) ){
			if( (queuedUpCharacter == '-' || queuedUpCharacter == '.') && !spacebarCurrentlyDepressed){
				System.out.println("Added a \""+queuedUpCharacter+"\"");
				resultString+=queuedUpCharacter;
				queuedUpCharacter='\0';
			}
			
			if( (queuedUpCharacter == ' ' || queuedUpCharacter == '/') && spacebarCurrentlyDepressed){
				System.out.println("Added a \""+queuedUpCharacter+"\"");
				resultString+=queuedUpCharacter;
				queuedUpCharacter='\0';
			}
			
			if(sw.hasBeenStarted() && spacebarCurrentlyDepressed){
				if(sw.getTime()<unitLength*1.5){
					if(queuedUpCharacter!='.'){
						queuedUpCharacter='.';
						System.out.println("Next character is decided to be a \".\"");
					}
				}
				else{
					if(queuedUpCharacter!='-'){
						queuedUpCharacter='-';
						System.out.println("Next character is decided to be a \"-\"");
					}
				}
			}
			
			if(sw.hasBeenStarted() && !spacebarCurrentlyDepressed){
				if(sw.getTime()>unitLength*3 && sw.getTime()<unitLength*10){
					if(queuedUpCharacter!=' '){
						queuedUpCharacter=' ';
						System.out.println("Next character is decided to be a \" \"");
					}
				}
				else {
					if(sw.getTime()>unitLength*10){
						if(queuedUpCharacter!='/'){
							queuedUpCharacter='/';
							System.out.println("Next character is decided to be a \"/\"");
						}
					}
				}
			}             
			
		}

		frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
	   
		return resultString;
	}

	public class MyKeyListener implements KeyListener {
		@Override
		public void keyTyped(KeyEvent e) {
		}

		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode()==KeyEvent.VK_SPACE && !spacebarCurrentlyDepressed){
				spacebarCurrentlyDepressed=true;
				sw.split();
			}
			else{
				if(e.getKeyCode()!=KeyEvent.VK_SPACE)
					System.out.println("keyPressed="+KeyEvent.getKeyText(e.getKeyCode()));
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			if(e.getKeyCode()==KeyEvent.VK_SPACE && spacebarCurrentlyDepressed){
				spacebarCurrentlyDepressed=false;
				sw.split();
			}
			else{
				if(e.getKeyCode()!=KeyEvent.VK_SPACE)
					System.out.println("keyReleased="+KeyEvent.getKeyText(e.getKeyCode()));
			}
		}
	}
}

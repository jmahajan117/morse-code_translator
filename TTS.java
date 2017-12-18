public class TTS {
	
	
	public TTS()
	{
		
	}
	
	public void say(String phrase)
	{
		TextToSpeech tts = new TextToSpeech();

		tts.speak(phrase, 2.0f, false, false);
	}
}

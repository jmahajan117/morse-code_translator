public class Stopwatch {
	private long lastSplit;
	boolean started;
	 
	public Stopwatch(){
		lastSplit=0;
		started=false;
	}
	
	public long split(){
		long difference=System.currentTimeMillis()-lastSplit;
		lastSplit=System.currentTimeMillis();
		//System.out.println("The split function has been called. lastSplit reset to "+lastSplit);
		started=true;
		return difference;
	}
	
	public long getTime(){
		return System.currentTimeMillis()-lastSplit;
	}
	
	public long stop(){
		long difference=System.currentTimeMillis()-lastSplit;
		lastSplit=System.currentTimeMillis();
		started=false;
		return difference;
	}
	
	public boolean hasBeenStarted(){
		return started;
	}
}

import java.util.Random;

public class Spinner {
	private String[] sections = {"Oink", "Squeal", "Snort", "GRUNT"};
	// This means that "oink" should happen 20% of the time, "Squeal" 50%, etc...
	// I am assuming the probabilities add to 1.
	private double[] probabilities = {.2, .5, .27, .03};
	private Random spinRandom;
	
	public Spinner(){
		spinRandom = new Random();
	}
	
	public String spin(){
		double spinNumber = spinRandom.nextDouble();
		return numToWord(spinNumber);
	}			
	
	/*
	 * Turn the random number into one of the spinner words 
	 * based on the given probabilities.
	 */
	public String numToWord(double spinNumber){	
		int index = 1;
		double low = 0;
		boolean done = false;
		String result = "";
		while(!done){
			double high = probabilities[index] + low;
			if(spinNumber>= low && spinNumber< high){
				result = sections[index];
				done = true;
			}
			else{
				low = high;
				index++;
			}
		}
		return result;
	}

}

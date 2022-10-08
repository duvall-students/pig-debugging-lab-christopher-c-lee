import java.util.Random;

public class Game {
	private Player player1;
	private Player player2;
	private Random die;
	private Spinner spinner;
	//private final String LOSER_SPIN = "grunt";
	//Spinning “GRUNT” doesn’t make the player lose all their points.
	//because spinner returns "GRUNT" and the code above tries to match to "grunt".
	private final String LOSER_SPIN = "GRUNT";
	private final int LOSER_ROLL = 1;
	
	public Game(){
//		Player player1 = new GUIPlayer();
//		Player player2 = new ComputerPlayer();
//		causes nullPointerException because it's saving the objects
//		as local variables, not instance variables.
		this.player1 = new GUIPlayer();
		this.player2 = new ComputerPlayer();
		die = new Random();
		spinner = new Spinner();
	}
	
	/*
	 * Method will play one game between the players.
	 */
	public void playGame(){
		printStartGameMessage();
		Player whoseTurn = player1;		
		while(!winner()){
			int roundScore = takeATurn(whoseTurn);
			whoseTurn.addToScore(roundScore);
			// Switch whose turn it is.
			if(whoseTurn == player1){
				whoseTurn = player2;
			}
			else{
				whoseTurn = player1;
			}
		}
		printEndGameMessage();
	}
	
	/*
	 * One player's turn.  Ends because
	 * - roll a 1
	 * - spin a "GRUNT"
	 * - or Player decides to stop
	 * 
	 * Return the number of points to add to the player
	 */
	public int takeATurn(Player whoseTurn){
		int roundScore = 0;
		boolean keepGoing = true;
		printStartRoundMessage(whoseTurn);
		while(keepGoing){
			//int roll = die.nextInt(7);
			//nextInt(7) returns integer from 0 to 6. therefore we need to add 1 to nextInt(6) 6 to get 1-6.
			int roll = die.nextInt(6) + 1;
			String spin = spinner.spin();
			System.out.println((roll)+ " "+ spin);
			
//			if(roll == LOSER_ROLL){
//				System.out.println("Lose a turn.");
//				return 0;
//			}
//			else if(spin == LOSER_SPIN.toUpperCase()){
//				System.out.println("Too bad!  Lose all your points.");
//				whoseTurn.resetScore();
//				return 0;
//			}
			
			//we should check if spinner before the role 
			//because if spin == "GRUNT",
			//they lost the turn AND the points, regardless of their role. 
			
			if(spin == LOSER_SPIN.toUpperCase()){
				System.out.println("Too bad!  Lose all your points.");
				whoseTurn.resetScore();
				return 0;
			}
			
			if(roll == LOSER_ROLL){
				System.out.println("Lose a turn.");
				return 0;
			}
			
			else{
				roundScore = roundScore + roll;
				System.out.println("Round total is currently: "+roundScore);
				keepGoing = whoseTurn.rollAgain(roundScore);
			}
		}
		return roundScore;
	}
	
	// True if one of the players has won the game.
	public boolean winner(){
//		return player1.hasWon() && player2.hasWon();
//we want the game to be over when one of the players has won, not both.
		return (player1.hasWon() || player2.hasWon());
	}
	
	/* 
	 * These methods are for printing messages to the console to follow the game.
	 */
	public void printStartRoundMessage(Player whoseTurn){
		System.out.println("New Round!  "+ whoseTurn.getName()+" 's turn."); 
		System.out.println(player1);
		System.out.println(player2);
	}
	
	public void printEndGameMessage(){
		if(player1.hasWon()){
			System.out.println("Winner is "+player1.getName());
		}
		else{
			System.out.println("Winner is "+player2.getName());
		}
	}
	
	public void printStartGameMessage(){
		System.out.println("Let's Play Pig!");
	}
}

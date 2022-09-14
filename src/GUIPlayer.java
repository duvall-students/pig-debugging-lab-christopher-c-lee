import java.util.Scanner;

public class GUIPlayer extends Player {
	private Scanner scanner;
	
	public GUIPlayer(){
		super("Human");
		System.out.println("Hello Player!  Please enter your name.");
		scanner = new Scanner(System.in);
		String name = scanner.nextLine();
		myName = name;
	}

	@Override
	public boolean rollAgain(int totalSoFar) {
		System.out.println("Score is "+myScore+ " Round score is "+totalSoFar);
		System.out.println("Do you want to roll again? Y/N");
		String answer = scanner.nextLine();
		return answer.toUpperCase().charAt(0) == 'Y';
	}
}

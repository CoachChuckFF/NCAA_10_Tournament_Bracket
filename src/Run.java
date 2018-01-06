import java.io.*;
import java.util.*;

public class Run {
	public static TournamentScreen tournament;
	private static Random rand = new Random();

	public static void main (String[] args) {
		List<List<Team>> complete = executeDraft(args);
		List<Conference> conferences = loadConferences(args);
		

		MainFrame main = new MainFrame();
		main.add(tournament = new TournamentScreen(66, 11));
		main.setVisible(true);

		Iterator<Team> region1 = complete.get(0).iterator();	
		Iterator<Team> region2 = complete.get(1).iterator();
		Iterator<Team> region3 = complete.get(2).iterator();	
		Iterator<Team> region4 = complete.get(3).iterator();
		
		for (TeamButton tb : tournament.getTeamButtons()) {
			if (tb.getLeft() == null){
				if (region1.hasNext()){
					Team temp = region1.next();
					tb.setTeam(temp);
					tb.setText(temp.getName());
				} else if (region2.hasNext()){
					Team temp = region2.next();
					tb.setTeam(temp);
					tb.setText(temp.getName());
				} else if (region3.hasNext()){
					Team temp = region3.next();
					tb.setTeam(temp);
					tb.setText(temp.getName());
				} else if (region4.hasNext()){
					Team temp = region4.next();
					tb.setTeam(temp);
					tb.setText(temp.getName());
				}
			}
		}
	}

	private static List<List<Team>> executeDraft(String[] args){
		Scanner scan = null;       // for input
		PrintStream out = null;    // for output

		// Set up where to send input and output
		switch (args.length) {

		//use console input and output
		case 0: 
			scan = new Scanner(System.in);
			out = new PrintStream(System.out);
			break;

			//use file input and console output
		case 1:
			out = new PrintStream(System.out);

			File inFile = new File(args[0]);

			try {
				scan = new Scanner(inFile);
			} catch (FileNotFoundException e) {
				System.out.println("File not found!");
			}

			break;

			//use file input and output
		case 3: 
			File inFile1 = new File(args[0]);
			File outFile1 = new File(args[1]);

			try {
				scan = new Scanner(inFile1);
				out = new PrintStream(outFile1);
			} catch (FileNotFoundException e) {
				System.out.println("File not found!");
			}

			break;

		default:
			System.err.println("Invalid command-line arguments");
			System.exit(0);
		}

		List<Player> participants = new ArrayList<Player>();
		List<Player> draftOrder = new ArrayList<Player>();

		out.print("who's playing?\n1. ");
		Player p1 = new Player(scan.nextLine());
		participants.add(p1);
		out.print("2. ");
		Player p2 = new Player(scan.nextLine());
		participants.add(p2);
		out.print("3. ");
		Player p3 = new Player(scan.nextLine());
		participants.add(p3);
		out.print("4. ");
		Player p4 = new Player(scan.nextLine());
		participants.add(p4);

		//generate random draft order
		while (!participants.isEmpty()){
			draftOrder.add(participants.remove(rand.nextInt(participants.size())));
		}

		out.println("\ndraft order:");
		for (Player p : draftOrder){
			out.println(p.getName());
		}

		out.println();

		//execute draft
		List<Team> fullList = new ArrayList<Team>();
		for (int round = 1; round <= 16; round++){
			//TODO: THIS IS WHERE THE DRAFT IS IMPLEMENTED
			textDraftRound(round, out, scan, draftOrder, fullList);
			out.println();
		}

		//create regions
		List<Team> midwest = createRegion (p1, p2, p3, p4);
		List<Team> east = createRegion (p2, p3, p4, p1);
		List<Team> south = createRegion (p3, p4, p1, p2);
		List<Team> west = createRegion (p4, p1, p2, p3);

		List<List<Team>> regions = new ArrayList<List<Team>>();
		regions.add(midwest);
		regions.add(east);
		regions.add(south);
		regions.add(west);

		//print draft results to a file
		File draftResults = new File("Draft Results.txt");
		PrintStream writer = null;
		try {
			writer = new PrintStream(draftResults);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		writer.println("Draft Results:");
		for (int i = 1; i < fullList.size()+1; i++){
			writer.println(i + ". " + fullList.get(i-1));
		}
		return regions;
	}
	
	public static List<Conference> loadConferences (String[]args){
		Scanner scan = null;
		List<Conference> conferences = new ArrayList<Conference>();

		try {
			scan = new Scanner(new File (args[2]));
		} catch (FileNotFoundException e) {
			System.out.println("File not found!");
		}

		while (scan.hasNextLine()) {
			String line = scan.nextLine();
			String[] parse = line.split(",");
			Conference np = null;
			boolean found = false;

			for (Conference c : conferences) {
				if (c.getName().equals(parse[1])) {
					c.getTeams().add(new Team(parse[0]));
					found = true;
				} 
			}
			
			if (!found) {
				np = new Conference (parse[1]);
				np.getTeams().add(new Team (parse[0]));
				conferences.add(np);
			}
		}	
		return conferences;
	}

	public static List<Team> textDraftRound(int round, PrintStream out, Scanner scan, List<Player> order, List<Team> fullList){
		out.println("Round " + (round) + ": ");

		if (round % 2 == 1){
			for (int forward = 0; forward < 4; forward++){
				Player currPlayer = order.get(forward);
				out.print(currPlayer + ": ");
				Team toAdd = new Team(scan.nextLine(), round);
				currPlayer.getTeams().add(round-1, toAdd);
				fullList.add(toAdd);
			}
		} else {
			for (int backward = 3; backward >= 0; backward--){
				Player currPlayer = order.get(backward);
				out.print(currPlayer + ": ");
				Team toAdd = new Team(scan.nextLine(), round);
				currPlayer.getTeams().add(round-1, toAdd);
				fullList.add(toAdd);
			}
		}	
		return fullList;
	}

	public static List<Team> clickDraftRound(int round, PrintStream out, Scanner scan, List<Player> order, List<Team> fullList){
		out.println("Round " + (round) + ": ");

		if (round % 2 == 1){
			for (int forward = 0; forward < 4; forward++){
				Player currPlayer = order.get(forward);
				Team toAdd = new Team(scan.nextLine(), round);
				currPlayer.getTeams().add(round-1, toAdd);
				fullList.add(toAdd);
			}
		} else {
			for (int backward = 3; backward >= 0; backward--){
				Player currPlayer = order.get(backward);
				Team toAdd = new Team(scan.nextLine(), round);
				currPlayer.getTeams().add(round-1, toAdd);
				fullList.add(toAdd);
			}
		}	
		return fullList;
	}

	public static List<Team> createRegion(Player p1, Player p2, Player p3, Player p4){

		List<Team> temp = new ArrayList<Team>();
		List<Player> participants = new ArrayList<Player>();

		participants.add(p1);
		participants.add(p2);
		participants.add(p3);
		participants.add(p4);

		int count = 0;
		for (int i = 0; i < 4; i++){
			for (Player p : participants){
				temp.add(p.getTeams().get(count));
				count++;
			}
		}

		List<Team> toReturn = new ArrayList<Team>(15);

		toReturn.add(temp.get(0));	//1
		toReturn.add(temp.get(15));	//16
		toReturn.add(temp.get(7));	//8
		toReturn.add(temp.get(8));	//9
		toReturn.add(temp.get(4));	//5
		toReturn.add(temp.get(11));	//12
		toReturn.add(temp.get(3));	//4
		toReturn.add(temp.get(12));	//13
		toReturn.add(temp.get(5));	//6
		toReturn.add(temp.get(10));	//11
		toReturn.add(temp.get(2));	//3
		toReturn.add(temp.get(13));	//14
		toReturn.add(temp.get(6));	//7
		toReturn.add(temp.get(9));	//10
		toReturn.add(temp.get(1));	//2
		toReturn.add(temp.get(14));	//15

		return toReturn;
	}	
}

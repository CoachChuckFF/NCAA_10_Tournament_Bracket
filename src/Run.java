import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.io.*;
import java.util.*;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Run {
	public static TournamentScreen tournament;
	private static Random rand = new Random();
	private static Color highlight = Color.YELLOW;

	public static void main (String[] args) {
		List<Conference> conferences = loadConferences(args);
		Collections.sort(conferences.get(0).getTeams());
		List<List<Team>> complete = executeDraft(conferences);

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

	private static List<List<Team>> executeDraft(List<Conference> conferences){
		Scanner scan = new Scanner(System.in);       // for input
		PrintStream out = new PrintStream(System.out);    // for output

		// Set up where to send input and output

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

		DraftFrame draft = new DraftFrame();
		DraftSelectionPanel draftScreen = new DraftSelectionPanel(1, 3, conferences);
		JPanel top = new JPanel();
		JPanel middle = new JPanel();
		JLabel currentDraftee = new JLabel("Ready Player 1");
		currentDraftee.setHorizontalAlignment(SwingConstants.CENTER);
		top.setLayout(new GridLayout(1, 1, 5, 5));
		top.setBackground(Color.LIGHT_GRAY);
		top.add(currentDraftee);
		middle.setLayout(new GridLayout(17, 4, 5, 5));
		middle.setBackground(Color.LIGHT_GRAY);
		middle.add(new JLabel(draftOrder.get(0).getName()));
		middle.add(new JLabel(draftOrder.get(1).getName()));
		middle.add(new JLabel(draftOrder.get(2).getName()));
		middle.add(new JLabel(draftOrder.get(3).getName()));
		for (int i = 1, j = 0; i <= 64; i++) {
			if(i % 4 == 1)
				j++;
			JLabel temp = new JLabel(j + ". ");
			temp.setOpaque(true);
			temp.setBackground(Color.WHITE);
			middle.add(temp);
		}

		draft.add(top, BorderLayout.NORTH);
		draft.add(middle, BorderLayout.CENTER);
		draft.add(draftScreen, BorderLayout.SOUTH);
		draft.setVisible(true);

		boolean draftRun = true;
		boolean forward = true;
		int forwardCount = 0;
		int tempIndex = 0;
		int draftIndex = 0;
		int totalPicks = 0;
		int round = 0;
		
		currentDraftee.setText("Now Drafting: " + draftOrder.get(draftIndex).getName());
		((JLabel)middle.getComponent(totalPicks+4)).setBackground(highlight);

		while(draftRun) {
			if(draftScreen.getSubmitButton().getSubmit()){	
				
				if (totalPicks % 4 == 0) {
					round++;
				}
				
				Team toAdd = draftScreen.getSelectedTeam();
				toAdd.setRank(round);
				draftOrder.get(draftIndex).getTeams().add(toAdd);
				fullList.add(toAdd);
				
				if((totalPicks+4) % 8 == 0 && totalPicks != 0){
					forward = false;
					tempIndex = totalPicks;
				}
				
				if(!forward) {
					((JLabel)middle.getComponent(tempIndex+8-(++forwardCount))).setText(toAdd.getRank() + ". " + toAdd.getName());
					
					((JLabel)middle.getComponent(tempIndex+8-(forwardCount))).setBackground(Color.WHITE);
					
					if((tempIndex+8-(forwardCount)) % 8 == 0) {
						try {
						((JLabel)middle.getComponent(totalPicks+5)).setBackground(highlight);
						} catch(Exception e)
						{
							System.out.println("Fuck It");
						}
					}
					else {
						((JLabel)middle.getComponent(tempIndex+8-(forwardCount)-1)).setBackground(highlight);
					}
					
					
					if(forwardCount > 3){
						forwardCount = 0;
						forward = true;
					}
				} else {
					((JLabel)middle.getComponent(totalPicks+4)).setText(toAdd.getRank() + ". " + toAdd.getName());
					((JLabel)middle.getComponent(totalPicks+4)).setBackground(Color.WHITE);
					
					if((totalPicks + 5) % 8 == 0)
						((JLabel)middle.getComponent(totalPicks+8)).setBackground(highlight);
					else
						((JLabel)middle.getComponent(totalPicks+5)).setBackground(highlight);	
				}
								
				for (Conference c : conferences) {
					if(c.equals(draftScreen.getSelectedConference()) || c.getName().equals("All NCAA")){
						int index = conferences.indexOf(c);
						conferences.get(index).getTeams().remove(draftScreen.getSelectedTeam());
					}
				}
				if (round % 2 == 0) {
					draftIndex--;
				} else {
					draftIndex++;
				}
				if (draftIndex == -1) draftIndex = 0;
				if (draftIndex == 4) draftIndex = 3;			
				totalPicks++;
				
				currentDraftee.setText("Now Drafting: " + draftOrder.get(draftIndex).getName());
				
				draftScreen.resetSelection();
				draftScreen.getSubmitButton().setSubmit(false);
			}
			
			if (fullList.size() == 64) {
				draftRun = false;
				draft.dispose();
			}
			try {
				Thread.sleep(3);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		//GUI returns full list

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
		conferences.add(new Conference("All NCAA"));

		try {
			scan = new Scanner(new File ("CompleteList.txt"));
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
					Team toAdd = new Team (parse[0]);
					conferences.get(0).getTeams().add(toAdd);
					c.getTeams().add(toAdd);
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

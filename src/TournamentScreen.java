import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.*;

public class TournamentScreen extends JPanel{
	
	private ArrayList<TeamButton> buttons;
	private ArrayList<TeamButton> buttonsOrdered;
	private ArrayList<TeamButton> buttonsR;
	private ArrayList<TeamButton> buttonsOrderedR;
	private ArrayList<Integer> value, valueR, index, indexR;
	private ArrayList<JLabel> labels;
	private ButtonTree tree, treeR;
	private int x, y;
	
	//Main Panel for displaying the buttons, it gets placed onto the main frame in run.java
	public TournamentScreen(int x, int y){
		this.setLayout(new GridLayout(x, y));
		this.setBackground(Color.LIGHT_GRAY);
		this.x = x;
		this.y = y;
		
		setScreen();
	}
	
	//returns the concatenated version of buttons and buttonsR
	public ArrayList<TeamButton> getTeamButtons(){
		ArrayList<TeamButton> all = new ArrayList<TeamButton>();
		all.addAll(buttons);
		all.addAll(buttonsR);
		return all;
	}
	
	//returns left tree root
	public ButtonTree getTreeL(){
		return tree;
	}
	
	//returns right tree root
	public ButtonTree getTreeR(){
		return treeR;
	}
	
	//sets the screen by calling a bunch of methods
	private void setScreen(){
		populatePosition();
		
		populateValue();
		
		populatePositionR();
		
		populateValueR();
		
		makeButtons();
		
		orderButtons();
		
		placeButtons();
		
		setLabels();
		
		makeTree();
	}

	//populates the value global with predetermined indexes and values
	private void populateValue() {
		// TODO Auto-generated method stub
		//super sweet hardcoding button positions
		value = new ArrayList<Integer>();
		
		value.add(11);
		value.add(23);
		value.add(33);
		value.add(46);
		value.add(55);
		value.add(67);
		value.add(77);
		value.add(91);
		value.add(99);
		value.add(111);
		value.add(121);
		value.add(134);
		value.add(143);
		value.add(155);
		value.add(165);
		value.add(180);
		value.add(187);
		value.add(199);
		value.add(209);
		value.add(222);
		value.add(231);
		value.add(243);
		value.add(253);
		value.add(267);
		value.add(275);
		value.add(287);
		value.add(297);
		value.add(310);
		value.add(319);
		value.add(331);
		value.add(341);
		value.add(346);
		value.add(374);
		value.add(386);
		value.add(396);
		value.add(409);
		value.add(418);
		value.add(430);
		value.add(440);
		value.add(454);
		value.add(462);
		value.add(474);
		value.add(484);
		value.add(497);
		value.add(506);
		value.add(518);
		value.add(528);
		value.add(543);
		value.add(550);
		value.add(562);
		value.add(572);
		value.add(585);
		value.add(594);
		value.add(606);
		value.add(616);
		value.add(630);
		value.add(638);
		value.add(650);
		value.add(660);
		value.add(673);
		value.add(682);
		value.add(694);
		value.add(704);
	}
	
	//populates the index global with predetermined indexes and values
	private void populatePosition(){
		index = new ArrayList<Integer>();
		
		//super sweet hardcoding button positions
		index.add(346);
		index.add(180);
		index.add(543);
		index.add(91);
		index.add(267);
		index.add(454);
		index.add(630);
		index.add(46);
		index.add(134);
		index.add(222);
		index.add(310);
		index.add(409);
		index.add(497);
		index.add(585);
		index.add(673);
		index.add(23);
		index.add(67);
		index.add(111);
		index.add(155);
		index.add(199);
		index.add(243);
		index.add(287);
		index.add(331);
		index.add(386);
		index.add(430);
		index.add(474);
		index.add(518);
		index.add(562);
		index.add(606);
		index.add(650);
		index.add(694);
		index.add(11);
		index.add(33);
		index.add(55);
		index.add(77);
		index.add(99);
		index.add(121);
		index.add(143);
		index.add(165);
		index.add(187);
		index.add(209);
		index.add(231);
		index.add(253);
		index.add(275);
		index.add(297);
		index.add(319);
		index.add(341);
		index.add(374);
		index.add(396);
		index.add(418);
		index.add(440);
		index.add(462);
		index.add(484);
		index.add(506);
		index.add(528);
		index.add(550);
		index.add(572);
		index.add(594);
		index.add(616);
		index.add(638);
		index.add(660);
		index.add(682);
		index.add(704);	
	}
	
	//populates the valueR global with predetermined indexes and values
	private void populateValueR(){
		valueR = new ArrayList<Integer>();
		
		valueR.add(21);
		valueR.add(31);
		valueR.add(43);
		valueR.add(52);
		valueR.add(65);
		valueR.add(75);
		valueR.add(87);
		valueR.add(95);
		valueR.add(109);
		valueR.add(119);
		valueR.add(131);
		valueR.add(140);
		valueR.add(153);
		valueR.add(163);
		valueR.add(175);
		valueR.add(182);
		valueR.add(197);
		valueR.add(207);
		valueR.add(219);
		valueR.add(228);
		valueR.add(241);
		valueR.add(251);
		valueR.add(263);
		valueR.add(271);
		valueR.add(285);
		valueR.add(295);
		valueR.add(307);
		valueR.add(316);
		valueR.add(329);
		valueR.add(339);
		valueR.add(351);
		valueR.add(379);
		valueR.add(384);
		valueR.add(394);
		valueR.add(406);
		valueR.add(415);
		valueR.add(428);
		valueR.add(438);
		valueR.add(450);
		valueR.add(458);
		valueR.add(472);
		valueR.add(482);
		valueR.add(494);
		valueR.add(503);
		valueR.add(516);
		valueR.add(526);
		valueR.add(538);
		valueR.add(545);
		valueR.add(560);
		valueR.add(570);
		valueR.add(582);
		valueR.add(591);
		valueR.add(604);
		valueR.add(614);
		valueR.add(626);
		valueR.add(634);
		valueR.add(648);
		valueR.add(658);
		valueR.add(670);
		valueR.add(679);
		valueR.add(692);
		valueR.add(702);
		valueR.add(714);
	}
	
	//populates the indexR global with predetermined indexes and values
	private void populatePositionR(){
		indexR = new ArrayList<Integer>();
		
		indexR.add(379);
		indexR.add(182);
		indexR.add(545);
		indexR.add(95);
		indexR.add(271);
		indexR.add(458);
		indexR.add(634);
		indexR.add(52);
		indexR.add(140);
		indexR.add(228);
		indexR.add(316);
		indexR.add(415);
		indexR.add(503);
		indexR.add(591);
		indexR.add(679);
		indexR.add(31);
		indexR.add(75);
		indexR.add(119);
		indexR.add(163);
		indexR.add(207);
		indexR.add(251);
		indexR.add(295);
		indexR.add(339);
		indexR.add(394);
		indexR.add(438);
		indexR.add(482);
		indexR.add(526);
		indexR.add(570);
		indexR.add(614);
		indexR.add(658);
		indexR.add(702);
		indexR.add(21);
		indexR.add(43);
		indexR.add(65);
		indexR.add(87);
		indexR.add(109);
		indexR.add(131);
		indexR.add(153);
		indexR.add(175);
		indexR.add(197);
		indexR.add(219);
		indexR.add(241);
		indexR.add(263);
		indexR.add(285);
		indexR.add(307);
		indexR.add(329);
		indexR.add(351);
		indexR.add(384);
		indexR.add(406);
		indexR.add(428);
		indexR.add(450);
		indexR.add(472);
		indexR.add(494);
		indexR.add(516);
		indexR.add(538);
		indexR.add(560);
		indexR.add(582);
		indexR.add(604);
		indexR.add(626);
		indexR.add(648);
		indexR.add(670);
		indexR.add(692);
		indexR.add(714);
	}

	//this makes the buttons in relation to their value
	private void makeButtons(){
		int count = 0;
		
		buttons = new ArrayList<TeamButton>();
		buttonsOrdered = new ArrayList<TeamButton>();
		buttonsR = new ArrayList<TeamButton>();
		buttonsOrderedR = new ArrayList<TeamButton>();
		
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				if(value.contains(count))
				{
					buttons.add(new TeamButton(value.indexOf(count)));
				}
				if(valueR.contains(count))
				{
					buttonsR.add(new TeamButton(valueR.indexOf(count)));
				}
		
				count++;
			}
		}
	}

	//this sorts the buttons into the index of which we need to add them (we have to add them by inserting column by column)
	private void orderButtons(){
		for (Integer values : index) {
			buttonsOrdered.add(buttons.get(value.indexOf(values)));
		}
		for (Integer values : indexR) {
			buttonsOrderedR.add(buttonsR.get(valueR.indexOf(values)));
		}
	}
	
	//this places the buttons in correct order on the panel
	private void placeButtons(){
		int count = 0;
		int buttonCount = 0;
		int buttonCountR = 0;
		int labelCount = 0;
		labels = new ArrayList<JLabel>();
		
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				if(value.contains(count)){
					this.add(buttons.get(buttonCount));
					buttonCount++;
				} else if(valueR.contains(count)){
					this.add(buttonsR.get(buttonCountR));
					buttonCountR++;
				} else {
					labels.add(new JLabel("", SwingConstants.CENTER));
					this.add(labels.get(labelCount));
					labels.get(labelCount).setFont(new Font ("Times", Font.BOLD, 12));
					labelCount++;
				}
				//this.add(buttons.get(count));
				count++;
			}
		}
	}
	
	//this places the labels for each region and the date
	private void setLabels(){
		labels.get(0).setText("Midwest");
		labels.get(0).setFont(new Font("Times", Font.BOLD, 12));
		
		labels.get(10).setText("South");
		labels.get(10).setFont(new Font("Times", Font.BOLD, 12));
		
		labels.get(300).setText("East");
		labels.get(300).setFont(new Font("Times", Font.BOLD, 12));
		
		labels.get(310).setText("West");
		labels.get(310).setFont(new Font("Times", Font.BOLD, 12));
		
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yy");
		labels.get(5).setText(dateFormat.format(new Date()));
		labels.get(5).setFont(new Font("Times", Font.ITALIC, 12));
	}

	//this creates the left and right tree of the buttons
 	private void makeTree()
	{
		tree = new ButtonTree();
		
		for (TeamButton teamButton : buttonsOrdered) 
		{
			//System.out.println(teamButton.getButtonValue());
			tree.insert(teamButton);
		}
		
		treeR = new ButtonTree();
		
		for (TeamButton teamButton : buttonsOrderedR) 
		{
			//System.out.println(teamButton.getButtonValue());
			treeR.insert(teamButton);
		}
	}
}


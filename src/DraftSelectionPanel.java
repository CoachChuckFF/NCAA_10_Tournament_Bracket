import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JPanel;

public class DraftSelectionPanel extends JPanel{
	
	private int x, y;
	private DraftSubmitButton submit;
	private List<Conference> conferenceList;
	private JComboBox<Conference> conferences;
	private JComboBox<Team> teams;
	
	//Main Panel for displaying the buttons, it gets placed onto the main frame in run.java
	public DraftSelectionPanel(int x, int y, List<Conference> c){
		this.setLayout(new GridLayout(x, y, 15, 50));
		this.setBackground(Color.LIGHT_GRAY);
		this.x = x;
		this.y = y;
		this.conferenceList = c;
		
		submit = new DraftSubmitButton();
		conferences = new JComboBox<Conference>();
		teams = new JComboBox<Team>();
		
		populateScreen();
	}
	
	private void populateScreen() {
		
		for (Conference conference : conferenceList) {
			conferences.addItem(conference);
	    	for (Team t : conference.getTeams()) {
				teams.addItem(t);
			}
		}
		conferences.setSelectedItem(0);
		
		conferences.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {		    	
				resetSelection();
		    }
		});
		
		submit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				DraftSubmitButton source = (DraftSubmitButton)e.getSource();
				source.setSubmit(true);
			}
		});
		
		this.add(conferences);
		this.add(teams);
		this.add(submit);
	}
	
	public DraftSubmitButton getSubmitButton() {
		return submit;
	}
	
	public Conference getSelectedConference() {
		return  (Conference) conferences.getSelectedItem();
	}
	
	public Team getSelectedTeam() {
		return (Team) teams.getSelectedItem();
	}
	
	public void resetSelection() {
		
		Conference temp = (Conference) conferences.getSelectedItem();
		
		
		teams.removeAllItems();
		
		if(((Conference) conferences.getSelectedItem()).getTeams().isEmpty()) {
			conferences.removeItem(temp);
			conferences.setSelectedIndex(0);
		}
		
    	for (Team t : ((Conference) conferences.getSelectedItem()).getTeams()) {
			teams.addItem(t);
		}
	}
}


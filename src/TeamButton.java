import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class TeamButton extends JButton {

	private Team team; 
	private boolean active;
	private int value;
	private TeamButton parent, left, right;

	TeamButton(Integer num){
		this.value = num;
		this.active = true;
		this.parent = null;
		this.left = null;
		this.right = null;
		this.setFont(new Font("Times", Font.PLAIN, 10));

		this.setOpaque(true);
		this.setBorderPainted(false);

		this.setBackground(Color.WHITE);
		
		this.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				TeamButton sibling = null;
				TeamButton source = (TeamButton)e.getSource();
				try {
					if (source.getTeam() == null){
						Team left = source.getLeft().getTeam();
						Team right = source.getRight().getTeam();
						Team high, low; 
						if (left.getRank() < right.getRank()){
							high = left;
							low = right;
						} else {
							high = right;
							low = left;
						}

						if (left.getRank() == right.getRank()){
							source.setTeam(simulation( high, low, 1));
						} else {
							source.setTeam(simulation(high, low));
						}

						if(source.hasButtonParent()){
							if(source.getButtonParent().getLeft().equals(source)){
								sibling = source.getButtonParent().getRight();
							} else {
								sibling = source.getButtonParent().getLeft();
							}
						}

						source.setTeam(source.team);
						source.setText(source.team.getName());
						if (source.getLeft().getTeam().equals(source.getTeam())){
							source.getRight().setBackground(Color.RED);
							source.getLeft().setBackground(Color.GREEN);
						} else {
							source.getRight().setBackground(Color.GREEN);
							source.getLeft().setBackground(Color.RED);
						}
						//source.setBackground(source.getTeam().getColor());
					} else if (source.hasButtonParent()) {
						if(source.getButtonActive()){
							if(source.hasButtonParent()){
								if(source.getButtonParent().getLeft().equals(source)){
									sibling = source.getButtonParent().getRight();
								} else {
									sibling = source.getButtonParent().getLeft();
								}
								sibling.setBackground(Color.RED);
								source.setBackground(Color.GREEN);
								parent.setTeam(source.team);
								parent.setText(source.team.getName());
								//parent.setBackground(source.getTeam().getColor());
							}
						}
					} else {
						if(source.equals(Run.tournament.getTreeL().getRoot())){
							source.setBackground(Color.GREEN);
							Run.tournament.getTreeR().getRoot().setBackground(Color.RED);
						} else {
							source.setBackground(Color.GREEN);
							Run.tournament.getTreeL().getRoot().setBackground(Color.RED);
						}
					}
				} catch (NullPointerException ed){
					System.out.println("Stop trying to simulate games without teams.");
				}
			}
		});
		this.setVisible(true);
	}

	public static Team simulation (Team high, Team low){
		int num = (int)(Math.random()*(10));
		if (num <= 3){
			return low;
		} else {
			return high;
		}
	}

	public static Team simulation (Team high, Team low, int i){
		int num = (int)(Math.random()*(10));
		if (num <= 5){
			return low;
		} else {
			return high;
		}
	}

	protected boolean hasButtonParent() {
		if(parent == null)
			return false;
		else
			return true;
	}

	public boolean getButtonActive(){
		return active;
	}

	public int getButtonValue() {
		return value;
	}

	public TeamButton getLeft() {
		return left;
	}

	public TeamButton getRight() {
		return right;
	}

	public  TeamButton getButtonParent(){
		return parent;
	}

	public Team getTeam(){
		return team;
	}

	public void setTeam(Team t){
		this.team = t;
	}

	public void setButtonActive(boolean active){
		this.active = active;
	}

	public void setButtonParent(TeamButton parent){
		this.parent = parent;
	}

	public void setLeft(TeamButton node) {
		this.left = node;
	}

	public void setRight(TeamButton node) {
		this.right = node;
	}
}

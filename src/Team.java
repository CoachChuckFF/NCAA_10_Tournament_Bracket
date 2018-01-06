import java.awt.Color;

public class Team implements Comparable{
	private String name;
	private int rank; 
	private Color color;
		
	public Team(String n) {
		this.name = n;
	}
	
	public Team(String n, int r){
		this.name = n;
		this.rank = r;
	}

	public Team(String n, int r, Color c){
		this.name = n;
		this.rank = r;
		this.color = c;
	}
	
	public void setName(String n){
		this.name = n;
	}
	
	public void setRank(int r){
		this.rank = r;
	}
	
	public String getName(){
		return name;
	}
	
	public int getRank(){
		return rank;
	}
	
	public Color getColor(){
		return color;
	}
	
	public String toString(){
		return name;
	}

	@Override
	public int compareTo(Object other) {
		int compareInt = this.getName().compareTo(((Team) other).getName());
		if (compareInt < 0) return -1;
		else return 1;
	}

}

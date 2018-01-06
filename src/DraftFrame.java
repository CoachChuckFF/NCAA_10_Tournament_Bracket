import java.awt.Color;
import java.awt.Image;

import javax.swing.JFrame;

public class DraftFrame extends JFrame{

	private final int HEIGHT = 800;
	private final int WIDTH = 1000;
	
	public DraftFrame()
	{

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setTitle("NCAA Draft");
	    this.setLocation(0,0);
	    this.setSize(WIDTH, HEIGHT);
		this.setVisible(true);
	}
	
}
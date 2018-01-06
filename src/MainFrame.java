import java.awt.Color;
import java.awt.Image;

import javax.swing.JFrame;

public class MainFrame extends JFrame{

	private final int HEIGHT = 1600;
	private final int WIDTH = 2000;
	
	public MainFrame()
	{

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setTitle("NCAA Tournament");
	    this.setLocation(0,0);
	    this.setSize(WIDTH, HEIGHT);
		this.setVisible(true);
	}
	
}

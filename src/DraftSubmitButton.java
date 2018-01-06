import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class DraftSubmitButton extends JButton {
	
	private boolean submit = false;


	@SuppressWarnings("deprecation")
	DraftSubmitButton(){

		this.setFont(new Font("Times", Font.PLAIN, 10));

		this.setOpaque(true);
		this.setBorderPainted(false);

		this.setBackground(Color.WHITE);
		
		this.setLabel("DRAFT");
		
		this.setVisible(true);
	}

	public void setSubmit(boolean submit) {
		this.submit = submit;
	}
	
	public boolean getSubmit() {
		return this.submit;
	}
}

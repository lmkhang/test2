package views;

import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Main extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	JTextField textfield1;

	JLabel label1;

	public void init() {
		setLayout(new FlowLayout());
		label1 = new JLabel("max 10 chars");
		textfield1 = new JTextField(15);
		add(label1);
		add(textfield1);
		textfield1.setDocument(new IPTextField(0,255));

		setSize(300, 300);
		setVisible(true);
	}
	public static void main(String[] args) {
		Main f = new Main();
		f.init();
		f.setVisible(true);
		
	}
}
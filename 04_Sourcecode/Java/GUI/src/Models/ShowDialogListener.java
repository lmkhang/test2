package Models;

import java.awt.event.*;
import javax.swing.*;

/**
 * A Java class to demonstrate how to put a scrolling text area in a JOptionPane
 * showMessageDialog dialog.
 * 
 * Steps are simple - Just create a JTextArea, wrap it in a JScrollPane, and
 * then add the JScrollPane to the showMessageDialog.
 */
public class ShowDialogListener implements ActionListener {
	private String longMessage;

	public ShowDialogListener(String longMessage) {
		this.longMessage = longMessage;
	}

	public void actionPerformed(ActionEvent e) {
		// create a JTextArea
		JTextArea textArea = new JTextArea(15, 35);
		textArea.setText(this.longMessage);
		textArea.setEditable(false);

		// wrap a scrollpane around it
		JScrollPane scrollPane = new JScrollPane(textArea);
		JButton btn_export = new JButton("Export");
		scrollPane.add(btn_export);
		// display them in a message dialog
		JOptionPane.showMessageDialog(null, scrollPane);
	}
}
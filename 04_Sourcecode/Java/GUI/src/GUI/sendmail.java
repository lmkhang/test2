/*
 * Created by JFormDesigner on Sat Jan 25 10:51:55 ICT 2014
 */

package GUI;

import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.util.Vector;

import javax.swing.*;
import javax.swing.border.*;

import Configs.Setting;
import Models.Mail;
import Thread.miniThread;
import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;

/**
 * @author Kunkka Lee
 */
public class sendmail extends JDialog {
	private main main;

	public sendmail(Frame owner) {
		super(owner);
		this.main = (GUI.main) owner;
		initComponents();
		this.setSize(500, 350);
		this.setLocation(500, 200);
	}

	public sendmail(Dialog owner) {
		super(owner);
		initComponents();
	}

	private void btn_send(MouseEvent e) {
		// TODO add your code here
		// TODO add your code here
		this.main.setLoading(this.btn_send);
		miniThread mini = new miniThread(this, "sendmail");
		mini.start();
	}
	
	public void resestButton(){
		URL image = this.getClass()
				.getResource("/outsrc/images/sendmail.png");
		ImageIcon icon = new ImageIcon(image);
		icon.getImage().flush();
		btn_send.setIcon(icon);
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY
		// //GEN-BEGIN:initComponents
		panel1 = new JPanel();
		txt_fullname = new JFormattedTextField();
		txt_youremail = new JFormattedTextField();
		txt_subject = new JFormattedTextField();
		scrollPane1 = new JScrollPane();
		txt_content = new JTextArea();
		btn_send = new JButton();

		//======== this ========
		setModal(true);
		setResizable(false);
		setTitle("Send mail to uss");
		setType(Window.Type.POPUP);
		Container contentPane = getContentPane();
		contentPane.setLayout(new GridBagLayout());
		((GridBagLayout)contentPane.getLayout()).columnWidths = new int[] {0, 0};
		((GridBagLayout)contentPane.getLayout()).rowHeights = new int[] {0, 0};
		((GridBagLayout)contentPane.getLayout()).columnWeights = new double[] {1.0, 1.0E-4};
		((GridBagLayout)contentPane.getLayout()).rowWeights = new double[] {1.0, 1.0E-4};

		//======== panel1 ========
		{
			panel1.setLayout(new GridBagLayout());
			((GridBagLayout)panel1.getLayout()).columnWidths = new int[] {0, 0};
			((GridBagLayout)panel1.getLayout()).rowHeights = new int[] {31, 26, 30, 134, 0, 0};
			((GridBagLayout)panel1.getLayout()).columnWeights = new double[] {1.0, 1.0E-4};
			((GridBagLayout)panel1.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};

			//---- txt_fullname ----
			txt_fullname.setBorder(new TitledBorder("Your name"));
			txt_fullname.setDragEnabled(true);
			panel1.add(txt_fullname, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
				new Insets(0, 0, 5, 0), 0, 0));

			//---- txt_youremail ----
			txt_youremail.setBorder(new TitledBorder("Your email"));
			txt_youremail.setDragEnabled(true);
			panel1.add(txt_youremail, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
				new Insets(0, 0, 5, 0), 0, 0));

			//---- txt_subject ----
			txt_subject.setBorder(new CompoundBorder(
				new TitledBorder("Subject"),
				Borders.DLU2_BORDER));
			txt_subject.setDragEnabled(true);
			panel1.add(txt_subject, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
				new Insets(0, 0, 5, 0), 0, 0));

			//======== scrollPane1 ========
			{
				scrollPane1.setBorder(new CompoundBorder(
					new TitledBorder("Content"),
					Borders.DLU2_BORDER));

				//---- txt_content ----
				txt_content.setWrapStyleWord(true);
				txt_content.setLineWrap(true);
				scrollPane1.setViewportView(txt_content);
			}
			panel1.add(scrollPane1, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 5, 0), 0, 0));

			//---- btn_send ----
			btn_send.setIcon(new ImageIcon(getClass().getResource("/outsrc/images/sendmail.png")));
			btn_send.setBorder(null);
			btn_send.setContentAreaFilled(false);
			btn_send.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					btn_send(e);
				}
			});
			panel1.add(btn_send, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
				new Insets(0, 0, 0, 0), 0, 0));
		}
		contentPane.add(panel1, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
			GridBagConstraints.CENTER, GridBagConstraints.BOTH,
			new Insets(0, 0, 0, 0), 0, 0));
		pack();
		setLocationRelativeTo(getOwner());
		// //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY
	// //GEN-BEGIN:variables
	public JPanel panel1;
	public JFormattedTextField txt_fullname;
	public JFormattedTextField txt_youremail;
	public JFormattedTextField txt_subject;
	public JScrollPane scrollPane1;
	public JTextArea txt_content;
	public JButton btn_send;
	// JFormDesigner - End of variables declaration //GEN-END:variables
}

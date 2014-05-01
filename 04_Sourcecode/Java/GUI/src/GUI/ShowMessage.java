/*
 * Created by JFormDesigner on Thu Mar 06 09:50:42 ICT 2014
 */

package GUI;

import java.awt.*;
import java.awt.event.*;
import java.io.Closeable;
import java.io.File;

import javax.swing.*;

import org.htmlcleaner.XPatherException;

import Configs.Setting;
import Utils.Utils;

import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;

/**
 * @author td4vn
 */
public class ShowMessage extends JFrame {

	public ShowMessage(String messageLog) {
		initComponents();
		JFrame.setDefaultLookAndFeelDecorated(true);
		this.setLocation(50, 50);
		this.setSize(500, 450);
		this.setVisible(true);
		this.setMessageLog(messageLog);
	}

	private void setMessageLog(String messageLog) {
		this.txt_msg_messagelog.setText(messageLog);
	}

	private void btn_msg_close(MouseEvent e) {
		// TODO add your code here
		this.hide();
	}

	private void btn_msg_export(MouseEvent e) throws XPatherException {
		// TODO add your code here
		// JFileChooser fileChooser = new JFileChooser();
		// fileChooser.setDialogTitle("Specify a file to save");
		//
		// int userSelection = fileChooser.showSaveDialog(this);
		// String rs = "Failed";
		// if (userSelection == JFileChooser.APPROVE_OPTION) {
		// File fileToSave = fileChooser.getSelectedFile();
		// Utils.writeExcelFromVector("referrals", fileToSave
		// .getAbsoluteFile().toString(), Setting.lv
		// .getListReferrals());
		// rs = "Success";
		// }
		// JOptionPane.showMessageDialog(this, rs);

		Utils.showExport("referrals", Setting.lv.getListReferrals());
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY
		// //GEN-BEGIN:initComponents
		pn_msg_log = new JScrollPane();
		txt_msg_messagelog = new JTextPane();
		panel1 = new JPanel();
		btn_msg_export = new JButton();

		// ======== this ========
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setVisible(true);
		setFont(new Font("Dialog", Font.PLAIN, 14));
		Container contentPane = getContentPane();
		contentPane.setLayout(new FormLayout("default:grow",
				"default:grow, $lgap, default"));

		// ======== pn_msg_log ========
		{

			// ---- txt_msg_messagelog ----
			txt_msg_messagelog.setEditable(false);
			pn_msg_log.setViewportView(txt_msg_messagelog);
		}
		contentPane.add(pn_msg_log, CC.xy(1, 1, CC.DEFAULT, CC.FILL));

		// ======== panel1 ========
		{
			panel1.setLayout(new GridBagLayout());
			((GridBagLayout) panel1.getLayout()).columnWidths = new int[] { 0,
					5, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
			((GridBagLayout) panel1.getLayout()).rowHeights = new int[] { 0, 0 };
			((GridBagLayout) panel1.getLayout()).columnWeights = new double[] {
					0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4 };
			((GridBagLayout) panel1.getLayout()).rowWeights = new double[] {
					0.0, 1.0E-4 };

			// ---- btn_msg_export ----
			btn_msg_export.setText("Export");
			btn_msg_export.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					try {
						btn_msg_export(e);
					} catch (XPatherException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			panel1.add(btn_msg_export,
					new GridBagConstraints(8, 0, 1, 1, 0.0, 0.0,
							GridBagConstraints.CENTER,
							GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0,
									0), 0, 0));
		}
		contentPane.add(panel1, CC.xy(1, 3));
		pack();
		setLocationRelativeTo(getOwner());
		// //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY
	// //GEN-BEGIN:variables
	private JScrollPane pn_msg_log;
	private JTextPane txt_msg_messagelog;
	private JPanel panel1;
	private JButton btn_msg_export;
	// JFormDesigner - End of variables declaration //GEN-END:variables
}
